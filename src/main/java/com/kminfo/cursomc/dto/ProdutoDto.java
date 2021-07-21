package com.kminfo.cursomc.dto;

import com.kminfo.cursomc.domain.Produto;

public class ProdutoDto  {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private double preco;
	
	public ProdutoDto() {
		
	}

	public ProdutoDto(Produto obj) {
		id = obj.getId();
		nome = obj.getNome();
		preco = obj.getPreco();
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
}
