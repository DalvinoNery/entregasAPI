package com.dalvino.entrega.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dalvino.entrega.api.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
			var cliente1 = new Cliente();
			cliente1.setId(1L);
			cliente1.setNome("Maria");
			cliente1.setEmail("maria@gmail.com");
			cliente1.setTelefone("63 984339544");
			
			var cliente2 = new Cliente();
			cliente2.setId(1L);
			cliente2.setNome("Pedro");
			cliente2.setEmail("pedro@gmail.com");
			cliente2.setTelefone("63 984390511");
			
			
		
		return Arrays.asList(cliente1, cliente2);
	}
}
