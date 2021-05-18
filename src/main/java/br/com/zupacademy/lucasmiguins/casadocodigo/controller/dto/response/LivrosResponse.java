package br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Livro;

public class LivrosResponse {
	
	private Long id;
	private String titulo;
	
	public LivrosResponse(Long id, String titulo) {
		this.id = id;
		this.titulo = titulo;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public static List<LivrosResponse> toLivrosResponse(List<Livro> livros) {
		return livros.stream().map(livro -> new LivrosResponse(livro.getId(), livro.getTitulo())).collect(Collectors.toList());
	}
}
