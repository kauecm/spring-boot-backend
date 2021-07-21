package com.kminfo.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kminfo.cursomc.domain.Categoria;
import com.kminfo.cursomc.domain.Cidade;
import com.kminfo.cursomc.domain.Cliente;
import com.kminfo.cursomc.domain.Endereco;
import com.kminfo.cursomc.domain.Estado;
import com.kminfo.cursomc.domain.ItemPedido;
import com.kminfo.cursomc.domain.Pagamento;
import com.kminfo.cursomc.domain.PagamentoBoleto;
import com.kminfo.cursomc.domain.PagamentoCartao;
import com.kminfo.cursomc.domain.Pedido;
import com.kminfo.cursomc.domain.Produto;
import com.kminfo.cursomc.domain.enums.EstadoPagamento;
import com.kminfo.cursomc.domain.enums.TipoCliente;
import com.kminfo.cursomc.repositories.CategoriaRepository;
import com.kminfo.cursomc.repositories.CidadeRepository;
import com.kminfo.cursomc.repositories.ClienteRepository;
import com.kminfo.cursomc.repositories.EnderecoRepository;
import com.kminfo.cursomc.repositories.EstadoRepository;
import com.kminfo.cursomc.repositories.ItemPedidoRepository;
import com.kminfo.cursomc.repositories.PagamentoRepository;
import com.kminfo.cursomc.repositories.PedidoRepository;
import com.kminfo.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Automovel");
		Categoria cat4 = new Categoria(null, "Eletronicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		Categoria cat8 = new Categoria(null, "Decoração");
		Categoria cat9 = new Categoria(null, "Cama mesa e banho");
		Categoria cat10 = new Categoria(null, "Moveis");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "mesa de escritorio", 380.00);
		Produto p5 = new Produto(null, "toalha", 80.00);
		Produto p6 = new Produto(null, "colcha", 200.00);
		Produto p7 = new Produto(null, "tv true color", 1280.00);
		Produto p8 = new Produto(null, "roçadeira", 800.00);
		Produto p9 = new Produto(null, "abajour", 100.00);
		Produto p10 = new Produto(null, "pendente", 180.00);
		Produto p11 = new Produto(null, "shampoo", 90.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3,p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4,p5,p6,p7,p8,p9,p10,p11));

		Estado est1 = new Estado(null, "Porto Alegre");
		Estado est2 = new Estado(null, "Sao Paulo");

		Cidade c1 = new Cidade(null, "Sapucaia do sul", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		


		
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Joao", "joao@gmail.com", "2222222222", TipoCliente.PESSOA_FISICA);

		cli1.getTelefones().addAll(Arrays.asList("51-992929292", "51-993939849"));

		Endereco e1 = new Endereco(null, "Henrique dias", "", "482", "Santa", "93214130", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "3888545", cli1, c2);

		cli1.getEndereco().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("03/07/2021 15:10"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("03/07/2021 10:10"), cli1, e2);

		Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("03/09/2021 15:10"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}
