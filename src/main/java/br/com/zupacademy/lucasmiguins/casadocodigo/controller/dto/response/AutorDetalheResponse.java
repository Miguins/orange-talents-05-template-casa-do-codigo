package br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.response;

import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Autor;

public class AutorDetalheResponse {
	
	private String nome;
	private String descricao;
	
	public AutorDetalheResponse(Autor autor) {
		this.nome = autor.getNome();
		this.descricao = autor.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
}
