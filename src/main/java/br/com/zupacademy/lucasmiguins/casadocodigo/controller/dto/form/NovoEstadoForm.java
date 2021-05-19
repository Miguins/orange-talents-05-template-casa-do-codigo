package br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.form;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Estado;
import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Pais;
import br.com.zupacademy.lucasmiguins.casadocodigo.validation.annotation.ExistsId;

public class NovoEstadoForm {
	
	@NotBlank
	private String nome;
	
	@NotNull
	@ExistsId(domainClass = Pais.class, fieldName = "id")
	private Long idPais;

	public NovoEstadoForm(@NotBlank String nome, @NotNull Long idPais) {
		this.nome = nome;
		this.idPais = idPais;
	}
	
	public Estado toModel(EntityManager em) {
		@NotNull Pais pais = em.find(Pais.class, idPais);
		
		return new Estado(nome, pais);
	}

	public Long getIdPais() {
		return idPais;
	}

	public String getNome() {
		return nome;
	}
}
