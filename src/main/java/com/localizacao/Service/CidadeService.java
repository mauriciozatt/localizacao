package com.localizacao.Service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.localizacao.Model.Cidade;
import com.localizacao.Repository.CidadesRepository;
import com.localizacao.Specs.CidadeSpecs;

@Service
public class CidadeService {

	private CidadesRepository cidadeRepository;

	public CidadeService(CidadesRepository cidadeRepository) {
		this.cidadeRepository = cidadeRepository;
	}

	// métodos

	public void SalvarCidade() {
		Cidade vMyCity = new Cidade(null, "Maravilha SC", 30000L);
		cidadeRepository.save(vMyCity);
	};

	public void RetornarCidades() {
		cidadeRepository.findAll().forEach(System.out::println);
	};

	public void RetornarPorNome() {
		cidadeRepository.findByNome("Brasilia").forEach(System.out::println);
	};

	public void RetornarPorHabitantes() {
		cidadeRepository.findByHabitantes(30000L).forEach(System.out::println);
	};

	public void RetornarPorNomeIniciandoCom() {
		cidadeRepository.findByNomeStartingWith("Port").forEach(System.out::println);
	};

	public void RetornarPorNomeTerminandoCom() {
		cidadeRepository.findByNomeEndingWith("o").forEach(System.out::println);
	};

	public void RetornarPorNomeContendo() {
		cidadeRepository.findByNomeContaining("Velho").forEach(System.out::println);
	};

	public void RetornarPorNomeLike() {
		cidadeRepository.findByNomeLike("%veLHo").forEach(System.out::println);
	};

	public void RetornarPorNomelikeOrdenado() {
		cidadeRepository.findByNomeLike("%%", Sort.by("habitantes", "nome").ascending()).forEach(System.out::println);
	};

	public void RetornarPorNomelikePaginado() {
		Pageable page = PageRequest.of(0, 2);
		cidadeRepository.findByNomeLike("%%", page).forEach(System.out::println);
	};

	public void RetornarPorHabMenor() {
		cidadeRepository.findByHabitantesLessThan(544545L).forEach(System.out::println);
	};

	public void RetornarPorHabMenorigual() {
		cidadeRepository.findByHabitantesLessThanEqual(30000L).forEach(System.out::println);
	};

	public void RetornarPorHabMaior() {
		cidadeRepository.findByHabitantesGreaterThan(30000L).forEach(System.out::println);
	};

	public void RetornarPorHabMenorENomeLike() {
		cidadeRepository.findByHabitantesLessThanAndNomeLike(999999L, "%Velho%").forEach(System.out::println);
	};

	public List<Cidade> FiltroDinamico(Cidade cidade) {

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase(true)
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

		// Recebe a minha entidade e o metcher que é basicamente um "filtro"
		Example<Cidade> example = Example.of(cidade, matcher);

		return cidadeRepository.findAll(example);

	};

	public void ListarCidadesSpecifications(String pNome, Long pQtd) {
		Specification<Cidade> specs = CidadeSpecs.nomeIgual(pNome).and(CidadeSpecs.habitantesMaior(pQtd));

		cidadeRepository.findAll(specs).forEach(System.out::println);
	};

	public void ListarCidadesSpecificationsFiltros(Cidade filtro) {
		// Retorna uma query com uma conjunção verdadeira...
		// Ex: Select * from cidades where 1=1
		Specification<Cidade> vQry = Specification
				.where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

		// Add a query as condições ...
		if (filtro.getId() != null) {
			vQry = vQry.and(CidadeSpecs.IdIgual(filtro.getId()));
		}

		if (StringUtils.hasText(filtro.getNome())) {
			vQry = vQry.and(CidadeSpecs.nomeLike(filtro.getNome()));
		}

		Long vQtd = filtro.getHabitantes();

		if ((vQtd != null) && (vQtd > 0)) {
			vQry = vQry.and(CidadeSpecs.habitanteIgual(vQtd));
		}

		cidadeRepository.findAll(vQry).forEach(System.out::println);

	};

	public void RetonarCidadePorNomeNativo(String nome) {
		cidadeRepository.findByNomeSQLNativo(nome).stream()
				.map(vCidadeProjection -> new Cidade(vCidadeProjection.getId(), vCidadeProjection.getNome(), null))
				.forEach(System.out::println);
	}

}
