package com.dalvino.entrega.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)// disponibilizar apenas os atributos que não estão nulos
@Getter
@Setter 
public class Problema {
	
	
	private Integer status;
	private OffsetDateTime dataHora;
	private String descricao;
	private List<Campo> campos;
	
	@AllArgsConstructor
	@Getter
	public static class Campo {
		
		private String nome;
		private String mensagem;
	}

}
