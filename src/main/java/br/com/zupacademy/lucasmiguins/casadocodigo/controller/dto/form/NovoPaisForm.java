package br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.form;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Pais;
import br.com.zupacademy.lucasmiguins.casadocodigo.validation.UniqueValue;

public class NovoPaisForm {
	
	@NotBlank
	@UniqueValue(domainClass = Pais.class, fieldName = "nome")
	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Pais toModel() {
		return new Pais(this.nome);
	}
}
