package br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Livro;

public class LivroResponse {
	
	private Long id;
	private String titulo;
	
	public LivroResponse(Long id, String titulo) {
		this.id = id;
		this.titulo = titulo;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public static List<LivroResponse> toLivrosResponse(List<Livro> livros) {
		return livros.stream().map(livro -> new LivroResponse(livro.getId(), livro.getTitulo())).collect(Collectors.toList());
	}
}
