package br.com.zupacademy.lucasmiguins.casadocodigo.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Length(max = 400)
	private String descricao;
	
	private LocalDateTime dataCriacao;

	public Autor(@NotBlank String nome, 
			@NotBlank @Email String email, 
			@NotBlank @Length(max = 400) String descricao) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
		this.dataCriacao = LocalDateTime.now();
	}
}
