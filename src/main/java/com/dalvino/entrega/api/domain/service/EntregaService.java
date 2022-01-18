package com.dalvino.entrega.api.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dalvino.entrega.api.Mapper.EntregaMapper;
import com.dalvino.entrega.api.domain.enumerate.StatusEntrega;
import com.dalvino.entrega.api.domain.model.Cliente;
import com.dalvino.entrega.api.domain.model.Entrega;
import com.dalvino.entrega.api.domain.repository.EntregaRepository;
import com.dalvino.entrega.api.representation.EntregaRepresentation;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EntregaService {

	private EntregaRepository entregaRepository;
	private ClienteService clienteService;
	private EntregaMapper entregaMapper;
	
	@Transactional
	public EntregaRepresentation adicionar(Entrega entrega) {
		Cliente cliente = clienteService.burcarPorId(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		entrega = entregaRepository.save(entrega);
		
		return entregaMapper.toModel(entrega);
	}
	
	
	public List<EntregaRepresentation> listar(){
		return entregaMapper.toListModel(entregaRepository.findAll());
	}
	
	public ResponseEntity<EntregaRepresentation> buscarPorId(Long id) {
		return entregaRepository.findById(id)
		.map(entrega ->ResponseEntity.ok(entregaMapper.toModel(entrega)))
		.orElse(ResponseEntity.notFound().build());
	}
}
