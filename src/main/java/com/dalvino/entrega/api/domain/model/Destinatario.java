package com.dalvino.entrega.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Destinatario {
	
	@NotBlank
	@Size(max = 255)
	@Column(name="nome_destinatario")
	private String nome;
	
	@NotBlank
	@Size(max = 510)
	@Column(name="logradouro_destinatario")
	private String logradouro;
	
	@NotBlank
	@Size(max = 10)
	@Column(name="numero_destinatario")
	private String numero;
	
	@NotBlank
	@Size(max = 510)
	@Column(name="complemento_destinatario")
	private String complemento;
	
	@NotBlank
	@Size(max = 255)
	@Column(name="bairro_destinatario")
	private String bairro;
}
