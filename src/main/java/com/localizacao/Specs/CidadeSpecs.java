package com.localizacao.Specs;

import org.springframework.data.jpa.domain.Specification;

import com.localizacao.Model.Cidade;

public abstract class CidadeSpecs {

	// Specification são cláusulas..... Tipo um SQLBuilder do Delphi....
	// root e minha fonte de dados <Cidade>
	// Query e minha query
	// criteriaBuilder e o objeto responsável por criar/manipular a minha Query

	public static Specification<Cidade> IdIgual(Long id) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
	};

	public static Specification<Cidade> nomeIgual(String nome) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("nome"), nome);
	};

	public static Specification<Cidade> habitanteIgual(Long qtd) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("habitantes"), qtd);
	};

	public static Specification<Cidade> nomeLike(String nome) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("nome")),
				"%"+nome+"%".toUpperCase());
	};

	public static Specification<Cidade> habitantesMaior(Long qtd) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("habitantes"), qtd);
	};

	public static Specification<Cidade> habitantesBetwenn(Long Ini, Long Fim) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("habitantes"), Ini, Fim);
	};

}
