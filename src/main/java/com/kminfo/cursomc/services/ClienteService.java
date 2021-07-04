package com.kminfo.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.kminfo.cursomc.domain.Categoria;
import com.kminfo.cursomc.domain.Cliente;
import com.kminfo.cursomc.dto.CategoriaDto;
import com.kminfo.cursomc.dto.ClienteDto;
import com.kminfo.cursomc.repositories.ClienteRepository;
import com.kminfo.cursomc.services.exceptions.DataIntegrityException;
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
		return clienteRepository.findAll();

	}
	

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return clienteRepository.save(obj);
	}
	
	public Cliente update(Cliente obj) {
		
		Cliente newObj = findById(obj.getId());
		updateData(newObj,obj);
		return clienteRepository.save(newObj);
	}
	
	
	public void delete(Integer id) {
		findById(id);
		try {
		clienteRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir pois possui entidades relacionadas");
		}
		}
	
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	

	public Cliente fromDto(ClienteDto objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null,null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
