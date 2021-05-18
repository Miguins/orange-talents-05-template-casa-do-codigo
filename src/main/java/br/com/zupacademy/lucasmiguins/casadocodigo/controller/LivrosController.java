package br.com.zupacademy.lucasmiguins.casadocodigo.controller;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.LivroResponse;
import br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.NovoLivroForm;
import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Livro;
import br.com.zupacademy.lucasmiguins.casadocodigo.repository.LivroRepository;

@RestController
@RequestMapping("/livros")
public class LivrosController {

	@Autowired LivroRepository livroRepository;
	
	@PersistenceContext	EntityManager em;
	
	@PostMapping
	@Transactional
	public ResponseEntity<NovoLivroForm> cadastrar(@RequestBody @Valid NovoLivroForm form) {
		
		Livro livro = form.toModel(em);
		em.persist(livro);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<LivroResponse> listar() {
		List<Livro> livros = livroRepository.findAll();
		
		return LivroResponse.toLivrosResponse(livros);
	}
}
