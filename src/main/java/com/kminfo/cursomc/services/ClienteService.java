package com.kminfo.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kminfo.cursomc.domain.Categoria;
import com.kminfo.cursomc.domain.Cliente;
import com.kminfo.cursomc.repositories.ClienteRepository;
import com.kminfo.cursomc.services.exceptions.ObjectNotFoundException;
@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id + "Tipo: " + Cliente.class.getName()));
		
	}
	
	public List<Cliente> findAll(){
		List<Cliente> obj = clienteRepository.findAll();
		return obj;
	}
}
