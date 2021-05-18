package br.com.zupacademy.lucasmiguins.casadocodigo.controller;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.form.NovoLivroForm;
import br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.response.LivroDetalheResponse;
import br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.response.LivrosResponse;
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
	public List<LivrosResponse> listar() {
		List<Livro> livros = livroRepository.findAll();
		
		return LivrosResponse.toLivrosResponse(livros);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LivroDetalheResponse> detalhar(@PathVariable Long id) {
		
		Optional<Livro> livro = livroRepository.findById(id);
		
		if (livro.isPresent()) {
			return ResponseEntity.ok(new LivroDetalheResponse(livro.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
}
