package com.dalvino.entrega.api.domain.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteIdRequest {

	@NotNull
	private Long id;
}
