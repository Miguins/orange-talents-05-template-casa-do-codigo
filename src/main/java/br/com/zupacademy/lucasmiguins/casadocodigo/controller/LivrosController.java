package br.com.zupacademy.lucasmiguins.casadocodigo.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.NovoLivroForm;
import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Livro;

@RestController
@RequestMapping("/livros")
public class LivrosController {

	@PersistenceContext	EntityManager em;
	
	@PostMapping
	@Transactional
	public ResponseEntity<NovoLivroForm> cadastrar(@RequestBody @Valid NovoLivroForm form) {
		
		Livro livro = form.toModel(em);
		em.persist(livro);
		
		return ResponseEntity.ok().build();
	}
}
