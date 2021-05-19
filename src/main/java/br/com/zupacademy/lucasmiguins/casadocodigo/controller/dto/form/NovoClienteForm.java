package br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.form;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Cliente;
import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Estado;
import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Pais;
import br.com.zupacademy.lucasmiguins.casadocodigo.validation.annotation.ExistsId;
import br.com.zupacademy.lucasmiguins.casadocodigo.validation.annotation.UniqueValue;

public class NovoClienteForm {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobrenome;
	
	@NotBlank
	@Email
	@UniqueValue(domainClass = Cliente.class, fieldName = "email")
	private String email;
	
	@NotBlank
	@UniqueValue(domainClass = Cliente.class, fieldName = "documento")
	private String documento;
	
	@NotBlank
	private String endereco;
	
	@NotBlank
	private String complemento;
	
	@NotBlank
	private String cidade;
	
	@NotNull
	@ManyToOne
	@ExistsId(domainClass = Pais.class, fieldName = "id")
	private Long idPais;
	
	@ManyToOne
	private Long idEstado;
	
	@NotBlank
	private String telefone;
	
	@NotBlank
	private String cep;

	public NovoClienteForm(@NotBlank String nome, @NotBlank String sobrenome, @NotBlank @Email String email,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String cidade, @NotNull Long idPais, Long idEstado, @NotBlank String telefone,
			@NotBlank String cep) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.idPais = idPais;
		this.idEstado = idEstado;
		this.telefone = telefone;
		this.cep = cep;
	}
	
	public Long getIdPais() {
		return idPais;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public boolean isDocumentoValido() {
		
		CPFValidator cpfValidator = new CPFValidator();
        CNPJValidator cnpjValidator = new CNPJValidator();
        
        cpfValidator.initialize(null);
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);
	}
	
	public Cliente toModel(EntityManager em) {
		@NotNull Pais pais = em.find(Pais.class, idPais);
		Cliente cliente = new Cliente(nome, sobrenome, email, documento, endereco, complemento, cidade, pais, telefone, cep);
		
		if (idEstado != null) {			
			Estado estado = em.find(Estado.class, idEstado);
			cliente.setEstado(estado);
		}
		
		return cliente;
	}
}
