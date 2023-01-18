package com.localizacao.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.localizacao.Model.Cidade;
import com.localizacao.Repository.Projections.CidadeProjections;

public interface CidadesRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

	List<Cidade> findByNome(String nome);

	// Quando uso projectios devo renomear meus retornos do SQL para deixar conforme a entidade...
	@Query(nativeQuery = true, value = "SELECT CID_ID AS ID, CID_NOME AS NOME FROM CIDADE WHERE CIDADE.CID_NOME = ?1")
	List<CidadeProjections> findByNomeSQLNativo(String nome);

	List<Cidade> findByHabitantes(Long habitantes);

	List<Cidade> findByNomeStartingWith(String nome);

	List<Cidade> findByNomeEndingWith(String nome);

	List<Cidade> findByNomeContaining(String nome);

	/*
	 * Quando uso HQL o meu SQL dever ser montado sobre a minha entity e não sobre o
	 * Banco...
	 */

	@Query(value = "select c from Cidade c where upper(c.nome) like upper(?1)")
	List<Cidade> findByNomeLike(String nome);

	@Query(value = "select c from Cidade c where upper(c.nome) like upper(?1)")
	List<Cidade> findByNomeLike(String nome, Sort sort);

	@Query(value = "select c from Cidade c where upper(c.nome) like upper(?1)")
	Page<Cidade> findByNomeLike(String nome, Pageable pageable);

	List<Cidade> findByHabitantesLessThan(Long habitantes);

	List<Cidade> findByHabitantesGreaterThan(Long habitantes);

	List<Cidade> findByHabitantesLessThanAndNomeLike(Long habitantes, String nome);

	List<Cidade> findByHabitantesLessThanEqual(Long habitantes);

}
