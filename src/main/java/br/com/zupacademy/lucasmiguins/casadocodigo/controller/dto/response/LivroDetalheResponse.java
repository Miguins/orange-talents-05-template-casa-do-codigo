package br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.response;

import java.math.BigDecimal;

import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Livro;

public class LivroDetalheResponse {

	private String titulo;
	
	private String resumo;
	
	private String sumario;
	
	private BigDecimal preco;
	
	private Integer paginas;
	
	private String isbn;
	
	private String dataPublicacao;
	
	private AutorDetalheResponse autor;

	public LivroDetalheResponse(Livro livro) {
		this.titulo = livro.getTitulo();
		this.resumo = livro.getResumo();
		this.sumario = livro.getSumario();
		this.preco = livro.getPreco();
		this.paginas = livro.getPaginas();
		this.isbn = livro.getIsbn();
		this.dataPublicacao = livro.getDataFormatada("dd/MM/yyyy");
		this.autor = new AutorDetalheResponse(livro.getAutor());
	}

	public String getTitulo() {
		return titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public String getSumario() {
		return sumario;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getDataPublicacao() {
		return dataPublicacao;
	}

	public AutorDetalheResponse getAutor() {
		return autor;
	}
}
