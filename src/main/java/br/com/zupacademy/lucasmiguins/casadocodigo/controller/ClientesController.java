package br.com.zupacademy.lucasmiguins.casadocodigo.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.form.NovoClienteForm;
import br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.response.ClienteResponse;
import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Cliente;
import br.com.zupacademy.lucasmiguins.casadocodigo.validation.EstadoPertencePaisClienteValidator;
import br.com.zupacademy.lucasmiguins.casadocodigo.validation.PaisPossuiEstadoClienteValidator;
import br.com.zupacademy.lucasmiguins.casadocodigo.validation.ProibeDocumentoInvalidoClienteValidator;

@RestController
@RequestMapping("/clientes")
public class ClientesController {
	
	@Autowired
	private PaisPossuiEstadoClienteValidator paisPossuiEstadoClienteValidator;
	
	@Autowired 
	private EstadoPertencePaisClienteValidator estadoPertenceAPaisValidator;
	
	@Autowired
	private ProibeDocumentoInvalidoClienteValidator proibeDocumentoInvalidoClienteValidator;
	
	@PersistenceContext
	private EntityManager em;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(proibeDocumentoInvalidoClienteValidator, estadoPertenceAPaisValidator, paisPossuiEstadoClienteValidator);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ClienteResponse> cadastrar(@RequestBody @Valid NovoClienteForm form) {
		
		Cliente cliente = form.toModel(em);
		em.persist(cliente);
				
		return ResponseEntity.ok(new ClienteResponse(cliente.getId()));
	}
}
