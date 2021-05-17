package br.com.zupacademy.lucasmiguins.casadocodigo.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.NovoAutorForm;
import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Autor;
import br.com.zupacademy.lucasmiguins.casadocodigo.repository.AutorRepository;

@Component
public class ProibeEmailDuplicadoAutorValidator implements Validator {
	
	@Autowired AutorRepository autorRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoAutorForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		
		NovoAutorForm form = (NovoAutorForm) target;
		Optional<Autor> autor = autorRepository.findByEmail(form.getEmail());
		
		if (autor.isPresent()) {
			errors.rejectValue("email", null, "Já existe um Autor com o email informado " + form.getEmail());
		}
	}

}
