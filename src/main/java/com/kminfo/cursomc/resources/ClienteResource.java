package com.kminfo.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kminfo.cursomc.domain.Cliente;
import com.kminfo.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		
		Cliente obj = clienteService.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping
	public ResponseEntity<?> findAll(){
		List<Cliente> obj = clienteService.findAll();
		return ResponseEntity.ok().body(obj);
	}
}
