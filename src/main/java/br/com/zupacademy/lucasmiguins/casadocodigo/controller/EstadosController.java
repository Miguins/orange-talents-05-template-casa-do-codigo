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

import br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.form.NovoEstadoForm;
import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Estado;
import br.com.zupacademy.lucasmiguins.casadocodigo.validation.EstadoUnicoPorPaisValidator;

@RestController
@RequestMapping("/estados")
public class EstadosController {
	
	@Autowired EstadoUnicoPorPaisValidator estadoUnicoPorPaisValidator;

	@PersistenceContext	EntityManager em;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(estadoUnicoPorPaisValidator);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<NovoEstadoForm> cadastrar(@RequestBody @Valid NovoEstadoForm form) {
		
		Estado estado = form.toModel(em);
		em.persist(estado);
		
		return ResponseEntity.ok().build();
	}
}
