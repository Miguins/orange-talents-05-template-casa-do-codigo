package br.com.zupacademy.lucasmiguins.casadocodigo.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ValidationErrorHandler {

	@Autowired
	private MessageSource messageSource;
	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class) 
	public List<ErroFormDTO> handleValidationError(MethodArgumentNotValidException exception) {
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		return buildValidationErrors(fieldErrors);
	}
	
	private List<ErroFormDTO> buildValidationErrors(List<FieldError> fieldErrors) {
		
		List<ErroFormDTO> dto = new ArrayList<>();
		
		fieldErrors.forEach(e -> {
			
			String mensagem = getErrorMessage(e);
			
			ErroFormDTO erro = new ErroFormDTO(e.getField(), mensagem);
			
			dto.add(erro);
		});
		
		return dto;
	}

	private String getErrorMessage(ObjectError error) {
		return messageSource.getMessage(error, LocaleContextHolder.getLocale());
	}
}
