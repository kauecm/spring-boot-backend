package com.kminfo.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kminfo.cursomc.domain.Categoria;
import com.kminfo.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		
		Categoria obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping
	public ResponseEntity<?> findAll(){
		List<Categoria> obj = service.findAll();
		
		return ResponseEntity.ok().body(obj);
	}
	

}
