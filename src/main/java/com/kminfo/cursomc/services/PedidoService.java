package com.kminfo.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kminfo.cursomc.domain.Categoria;
import com.kminfo.cursomc.domain.Pedido;
import com.kminfo.cursomc.repositories.PedidoRepository;
import com.kminfo.cursomc.services.exceptions.ObjectNotFoundException;
@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido findById(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id + "Tipo: " + Pedido.class.getName()));
		
	}
	
	public List<Pedido> findAll(){
		List<Pedido> obj = pedidoRepository.findAll();
		return obj;
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		return pedidoRepository.save(obj);
	}
	
	public Pedido update(Pedido obj) {
		findById(obj.getId());
		return pedidoRepository.save(obj);
	}
}
