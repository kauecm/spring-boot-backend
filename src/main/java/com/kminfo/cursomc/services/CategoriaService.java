package com.kminfo.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kminfo.cursomc.domain.Categoria;
import com.kminfo.cursomc.repositories.CategoriaRepository;
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
		List<Categoria> obj =  repository.findAll();
		return obj;
	}
	
}
