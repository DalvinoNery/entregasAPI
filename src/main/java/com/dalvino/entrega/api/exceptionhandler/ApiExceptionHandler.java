package com.dalvino.entrega.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dalvino.entrega.api.domain.exception.DomainException;

import lombok.AllArgsConstructor;

/*Classe utilizada para tratamento de exceções*/
@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	/*Utilizado para customização das mensagens, apresentando o que for configurado no arquivo messages.properties*/
	private MessageSource messageSource;
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Problema.Campo> listaCampos = new ArrayList<>();
		
		ex.getBindingResult().getAllErrors().forEach((ObjectError erro) -> {
			
			String nome = ((FieldError) erro).getField();
			String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
			
			listaCampos.add(new Problema.Campo(nome, mensagem));
		});
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setDescricao("Foram informado valores inválidos! Por favor, preencha corretamente e tente novamente.");
		problema.setCampos(listaCampos);
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@ExceptionHandler(DomainException.class)
	public ResponseEntity<Object> handleDominio(DomainException domainException, WebRequest request){
		HttpStatus  status  = HttpStatus.BAD_REQUEST;
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setDescricao(domainException.getMessage());
		
		return handleExceptionInternal(domainException, problema, new HttpHeaders(), status, request);
	}
}
