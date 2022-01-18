package com.dalvino.entrega.api.domain.request;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaRequest {

	@Valid
	@NotNull
	private ClienteIdRequest clienteIdRequest;
	
	@Valid
	@NotNull
	private DestinatarioRequest destinatario;
	
	private BigDecimal taxa;
		
}
