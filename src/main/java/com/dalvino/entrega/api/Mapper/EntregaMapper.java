package com.dalvino.entrega.api.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.dalvino.entrega.api.domain.model.Entrega;
import com.dalvino.entrega.api.domain.request.EntregaRequest;
import com.dalvino.entrega.api.representation.EntregaRepresentation;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaMapper {

	private ModelMapper modelMapper;
	
	
	public EntregaRepresentation toModel(Entrega entrega) {
		return modelMapper.map(entrega, EntregaRepresentation.class);
	}
	
	public List<EntregaRepresentation> toListModel(List<Entrega> listaEntregas){
		return listaEntregas.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Entrega toEntity(EntregaRequest entregaRequest) {
		return modelMapper.map(entregaRequest, Entrega.class);
	}
}
