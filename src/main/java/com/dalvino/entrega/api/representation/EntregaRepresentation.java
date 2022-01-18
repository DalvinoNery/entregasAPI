package com.dalvino.entrega.api.representation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.dalvino.entrega.api.domain.enumerate.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaRepresentation {
	
	private Long id;
	private ClienteResumoRepresentation cliente;
	private DestinatarioRepresentation destinatario;
	private BigDecimal taxa;
	private StatusEntrega status;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataEntrega;

}
