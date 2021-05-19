package br.com.zupacademy.lucasmiguins.casadocodigo.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.form.NovoClienteForm;

@Component
public class ProibeDocumentoInvalidoClienteValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoClienteForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		NovoClienteForm clienteForm = (NovoClienteForm) target;
		
        if(!clienteForm.isDocumentoValido()){
            errors.rejectValue("documento", null, "O Documento precisa ser no formato CPF ou CNPJ");
        }
	}

}