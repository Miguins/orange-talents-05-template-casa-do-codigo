package br.com.zupacademy.lucasmiguins.casadocodigo.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.form.NovoClienteForm;
import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Estado;
import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Pais;
import br.com.zupacademy.lucasmiguins.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.lucasmiguins.casadocodigo.repository.PaisRepository;

@Component
public class EstadoPertencePaisClienteValidator implements Validator {
	
	@Autowired EstadoRepository estadoRepository;
	@Autowired PaisRepository paisRepository;

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
		Optional<Pais> pais = paisRepository.findById(clienteForm.getIdPais());
		
		if (clienteForm.getIdEstado() != null) {
			
			Optional<Estado> estado = estadoRepository.findById(clienteForm.getIdEstado());
			
			if (!estado.isPresent()) {
				errors.rejectValue("idEstado", null, "O estado informado não existe");
			} else if (!estado.get().pertenceAPais(pais.get())) {
				errors.rejectValue("idEstado", null, "Este estado não pertence ao país selecionado.");
			}
		}
		
	}

}
