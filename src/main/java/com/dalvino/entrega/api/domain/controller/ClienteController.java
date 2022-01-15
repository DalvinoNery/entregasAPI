package com.dalvino.entrega.api.domain.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dalvino.entrega.api.domain.model.Cliente;
import com.dalvino.entrega.api.domain.repository.ClienteRepository;
import com.dalvino.entrega.api.domain.service.ClienteService;

import lombok.AllArgsConstructor;
import net.bytebuddy.asm.Advice.Return;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {


	private ClienteService clienteService;

	//Listar
	@GetMapping
	public List<Cliente> listar() {
		return clienteService.listar();
	}

	//Buscar por id
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		return clienteService.buscarPorId(clienteId);
	}
	
	//Adicionar e retornar cliente
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) //Utilizando para retornar o código correto para o recurso.
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return clienteService.salvar(cliente);
	}
	
	//Atualizar cliente
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId,@Valid @RequestBody Cliente cliente) {
		//verificando se existe o cliente com o ID informado
		if(!clienteService.verificarPorId(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		//caso exista, seta o clienteId no atributo id do cliente e salva.
		cliente.setId(clienteId);
		cliente = clienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	//Buscar por id
		@DeleteMapping("/{clienteId}")
		public ResponseEntity<Void> deletar(@PathVariable Long clienteId) {
			
			//verificando se existe o cliente com o ID informado
			if(!clienteService.verificarPorId(clienteId)) {
				return ResponseEntity.notFound().build();
			}
			
			//Se existir, exclui o objeto.
			clienteService.excluir(clienteId);
			
			return ResponseEntity.noContent().build();

		}
}
