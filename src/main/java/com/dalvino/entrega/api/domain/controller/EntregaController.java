package com.dalvino.entrega.api.domain.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dalvino.entrega.api.Mapper.EntregaMapper;
import com.dalvino.entrega.api.domain.model.Entrega;
import com.dalvino.entrega.api.domain.request.EntregaRequest;
import com.dalvino.entrega.api.domain.service.EntregaService;
import com.dalvino.entrega.api.representation.EntregaRepresentation;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaService entregaService;
	private EntregaMapper entregaMapper;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaRepresentation adicionar(@Valid @RequestBody Entrega entrega) {
		//Entrega entrega = entregaMapper.toEntity(entregaRequest);
		
		return entregaService.adicionar(entrega);
	}
	
	@GetMapping
	public List<EntregaRepresentation> listar(){
		return entregaService.listar();
	}
	
	//Buscar por id
		@GetMapping("/{entregaId}")
		public ResponseEntity<EntregaRepresentation> buscar(@PathVariable Long entregaId) {
			return entregaService.buscarPorId(entregaId);
		}
}
