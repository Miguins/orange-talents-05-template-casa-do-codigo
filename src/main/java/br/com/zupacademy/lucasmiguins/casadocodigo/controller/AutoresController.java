package br.com.zupacademy.lucasmiguins.casadocodigo.controller;

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

import br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.NovoAutorForm;
import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Autor;
import br.com.zupacademy.lucasmiguins.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.lucasmiguins.casadocodigo.validation.ProibeEmailDuplicadoAutorValidator;

@RestController
@RequestMapping(("/autores"))
public class AutoresController {
	
	@Autowired AutorRepository autorRepository;
	
	@Autowired ProibeEmailDuplicadoAutorValidator proibeEmailDuplicadoAutorValidator;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(proibeEmailDuplicadoAutorValidator);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<NovoAutorForm> novo(@RequestBody @Valid NovoAutorForm autorForm) {
		
		Autor autor = autorForm.toModel();
		autorRepository.save(autor);
		
		return ResponseEntity.ok(autorForm);
	}
}
