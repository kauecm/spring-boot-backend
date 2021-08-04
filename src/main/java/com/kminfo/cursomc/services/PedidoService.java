package com.kminfo.cursomc.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.kminfo.cursomc.domain.ItemPedido;
import com.kminfo.cursomc.domain.PagamentoBoleto;
import com.kminfo.cursomc.domain.Pedido;
import com.kminfo.cursomc.domain.enums.EstadoPagamento;
import com.kminfo.cursomc.repositories.ItemPedidoRepository;
import com.kminfo.cursomc.repositories.PagamentoRepository;
import com.kminfo.cursomc.repositories.PedidoRepository;
import com.kminfo.cursomc.services.exceptions.DataIntegrityException;
import com.kminfo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	

	public Pedido findById(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + "Tipo: " + Pedido.class.getName()));

	}

	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagto = (PagamentoBoleto) obj.getPagamento();
			boletoService.preencherPagamentoBoleto(pagto, obj.getInstante());
		}
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.findById(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}

	public Pedido update(Pedido obj) {
		findById(obj.getId());
		return pedidoRepository.save(obj);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			pedidoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir pois possui produtos");
		}
	}
}
