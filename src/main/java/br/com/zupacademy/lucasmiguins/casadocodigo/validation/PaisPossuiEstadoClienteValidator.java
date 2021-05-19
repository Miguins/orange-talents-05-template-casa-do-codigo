package br.com.zupacademy.lucasmiguins.casadocodigo.validation;

import java.util.List;
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
public class PaisPossuiEstadoClienteValidator implements Validator {

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
		
		List<Estado> paisPossuiEstados = estadoRepository.findByPais(pais.get());
		if (!paisPossuiEstados.isEmpty() && clienteForm.getIdEstado() == null) {
			errors.rejectValue("idEstado", null, "O País possui estado(s), um deve ser selecionado.");
		}
		
	}

}
