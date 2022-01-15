package com.dalvino.entrega.api.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dalvino.entrega.api.domain.exception.DomainException;
import com.dalvino.entrega.api.domain.model.Cliente;
import com.dalvino.entrega.api.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteService {
	
	ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		Boolean validaEmail = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		if(validaEmail) {
			throw new DomainException("Já existe um cliente cadastrado com o e-mail informado");
		}
		
		return clienteRepository.save(cliente);
	}
	
	public ResponseEntity<Cliente> buscarPorId(Long id) {
		return clienteRepository.findById(id)
		.map(ResponseEntity::ok)
		.orElse(ResponseEntity.notFound().build());
	}
	
	public List<Cliente> listar(){
		return clienteRepository.findAll();
	}
	
	public Boolean verificarPorId(Long id) {
		return clienteRepository.existsById(id);
	}
	
	public void excluir(Long id) {
		clienteRepository.deleteById(id);
	}

}
