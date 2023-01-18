package com.localizacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.localizacao.Service.CidadeService;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeService service;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Inicializando projeto (Projetinho para aprender JPA).......");
		// service.SalvarCidade();
		// service.RetornarCidades();
		// service.RetornarPorNome();
		// service.RetornarPorNomeContendo();
		// service.RetornarPorNomeIniciandoCom();
		// service.RetornarPorNomeTerminandoCom();
		// service.RetornarPorNomeLike();
		// service.RetornarPorNomelikeOrdenado();
		// service.RetornarPorNomelikePaginado();

		// service.RetornarPorHabitantes();
		// service.RetornarPorHabMenor();
		// service.RetornarPorHabMenorigual();
		// service.RetornarPorHabMaior();
		// service.RetornarPorHabMenorENomeLike();

		/*
		 * monta o filtro de acordo com meu objeto.... select * from cidades where
		 * cidades.nome like %velho% and cidades.habitantes = 5000
		 * 
		 */

		// var vCity = new Cidade(null, "Velho", 5000L);
		// service.FiltroDinamico(vCity).forEach(System.out::println);

		// service.ListarCidadesSpecifications("Brasilia", 500L);

		// var vCity = new Cidade(null, "Bra", 30000L);
		// service.ListarCidadesSpecificationsFiltros(vCity);

		service.RetonarCidadePorNomeNativo("Brasilia");

	};

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);

	};

}
