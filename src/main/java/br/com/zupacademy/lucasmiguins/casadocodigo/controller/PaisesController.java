package br.com.zupacademy.lucasmiguins.casadocodigo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.form.NovoPaisForm;
import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Pais;
import br.com.zupacademy.lucasmiguins.casadocodigo.repository.PaisRepository;

@RestController
@RequestMapping("/paises")
public class PaisesController {
	
	@Autowired PaisRepository paisRepository;
	
	@PostMapping
	public ResponseEntity<NovoPaisForm> cadastrar(@RequestBody @Valid NovoPaisForm form) {
		
		Pais pais = form.toModel();
		paisRepository.save(pais);
		
		return ResponseEntity.ok().build();
	}
}
