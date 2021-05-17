package br.com.zupacademy.lucasmiguins.casadocodigo.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.NovaCategoriaForm;
import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Categoria;
import br.com.zupacademy.lucasmiguins.casadocodigo.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {
	
	@Autowired CategoriaRepository categoriaRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<NovaCategoriaForm> nova(@RequestBody @Valid NovaCategoriaForm categoriaForm) {
		
		Categoria categoria = categoriaForm.toModel();
		categoriaRepository.save(categoria);
		
		return ResponseEntity.ok(categoriaForm);
	}
}
