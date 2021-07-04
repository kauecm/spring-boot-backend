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
import com.kminfo.cursomc.repositories.CategoriaRepository;
import com.kminfo.cursomc.services.exceptions.DataIntegrityException;
import com.kminfo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id + "Tipo: " + Categoria.class.getName()));
		
	}
	
	public List<Categoria> findAll(){
		return  repository.findAll();
	}
	

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		findById(obj.getId());
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
		repository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir pois possui produtos");
		}
		}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
}
