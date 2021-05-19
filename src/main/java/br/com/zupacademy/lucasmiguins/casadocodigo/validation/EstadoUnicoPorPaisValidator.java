package br.com.zupacademy.lucasmiguins.casadocodigo.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.lucasmiguins.casadocodigo.controller.dto.form.NovoEstadoForm;
import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Estado;
import br.com.zupacademy.lucasmiguins.casadocodigo.modelo.Pais;
import br.com.zupacademy.lucasmiguins.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.lucasmiguins.casadocodigo.repository.PaisRepository;

@Component
public class EstadoUnicoPorPaisValidator implements Validator {
	
	@Autowired EstadoRepository estadoRepository;
	@Autowired PaisRepository paisRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoEstadoForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		
		NovoEstadoForm estadoForm = (NovoEstadoForm) target;
		
		Estado estado = estadoRepository.findByNome(estadoForm.getNome());
		
		if (estado != null) {
			Optional<Pais> pais = paisRepository.findById(estadoForm.getIdPais());
			
			if (pais.isPresent()) {				
				errors.rejectValue("nome", null, "Já existe um estado no País selecionado com o mesmo nome.");
			}
		}
	}

}
