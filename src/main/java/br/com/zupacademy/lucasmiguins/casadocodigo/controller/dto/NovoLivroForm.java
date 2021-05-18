package br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Autor;
import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Categoria;
import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Livro;
import br.com.zupacademy.lucasmiguins.casadocodigo.validation.ExistsId;
import br.com.zupacademy.lucasmiguins.casadocodigo.validation.UniqueValue;

public class NovoLivroForm {
	
	@NotBlank
	@UniqueValue(domainClass = Livro.class, fieldName = "titulo")
	private String titulo;
	
	@NotBlank
	@Length(max = 500)
	private String resumo;
	
	private String sumario;
	
	@NotNull
	@Min(value = 20)
	private BigDecimal preco;
	
	@NotNull
	@Min(value = 100)
	private Integer paginas;
	
	@NotBlank
	@UniqueValue(domainClass = Livro.class, fieldName = "isbn")
	private String isbn;
	
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataPublicacao;
	
	@NotNull
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;
	
	@NotNull
	@ExistsId(domainClass = Autor.class, fieldName = "id")
	private Long idAutor;
	

	public NovoLivroForm(@NotBlank String titulo, @NotBlank @Length(max = 500) String resumo, String sumario,
			@NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) Integer paginas, @NotBlank String isbn,
			@NotNull Long idCategoria, @NotNull Long idAutor) {
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.paginas = paginas;
		this.isbn = isbn;
		this.idCategoria = idCategoria;
		this.idAutor = idAutor;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public Livro toModel(EntityManager em) {
		@NotNull Autor autor = em.find(Autor.class, this.idAutor);
		@NotNull Categoria categoria = em.find(Categoria.class, this.idCategoria);
		
		return new Livro(titulo, resumo, sumario, preco, paginas, isbn, dataPublicacao, categoria, autor);
	}
}
