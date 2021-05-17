package br.com.zupacademy.lucasmiguins.casadocodigo.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.NovaCategoriaForm;
import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Categoria;
import br.com.zupacademy.lucasmiguins.casadocodigo.repository.CategoriaRepository;

@Component
public class ProibeNomeDuplicadoCategoriaValidator implements Validator {
	
	@Autowired CategoriaRepository categoriaRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovaCategoriaForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		
		NovaCategoriaForm form = (NovaCategoriaForm) target;
		Optional<Categoria> categoria = categoriaRepository.findByNome(form.getNome());
		
		if (categoria.isPresent()) {
			errors.rejectValue("nome", null, "Já existe uma Categoria com o nome informado: " + form.getNome());
		}
	}
}
