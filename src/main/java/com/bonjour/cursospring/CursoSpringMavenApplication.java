package com.bonjour.cursospring;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bonjour.cursospring.domain.Categoria;
import com.bonjour.cursospring.domain.Cidade;
import com.bonjour.cursospring.domain.Cliente;
import com.bonjour.cursospring.domain.Endereco;
import com.bonjour.cursospring.domain.Estado;
import com.bonjour.cursospring.domain.Pagamento;
import com.bonjour.cursospring.domain.PagamentoComBoleto;
import com.bonjour.cursospring.domain.PagamentoComCartao;
import com.bonjour.cursospring.domain.Pedido;
import com.bonjour.cursospring.domain.Produto;
import com.bonjour.cursospring.domain.enums.EstadoPagamento;
import com.bonjour.cursospring.domain.enums.TipoCliente;
import com.bonjour.cursospring.repositories.CategoriaRepository;
import com.bonjour.cursospring.repositories.CidadeRepository;
import com.bonjour.cursospring.repositories.ClienteRepository;
import com.bonjour.cursospring.repositories.EnderecoRepository;
import com.bonjour.cursospring.repositories.EstadoRepository;
import com.bonjour.cursospring.repositories.PagamentoRepository;
import com.bonjour.cursospring.repositories.PedidoRepository;
import com.bonjour.cursospring.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoSpringMavenApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository repo;

	@Autowired
	private ProdutoRepository produtoRepo;

	@Autowired
	private EstadoRepository estadoRepo;

	@Autowired
	private CidadeRepository cidadeRepo;

	@Autowired
	private ClienteRepository clienteRepo;

	@Autowired
	private EnderecoRepository enderecoRepo;

	@Autowired
	private PedidoRepository pedidoRepo;

	@Autowired
	private PagamentoRepository pagamentoRepo;

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringMavenApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		repo.saveAll(Arrays.asList(cat1, cat2));
		produtoRepo.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepo.saveAll(Arrays.asList(est1, est2));
		cidadeRepo.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "55555", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("9999", "5555"));
		
		Endereco e1 = new Endereco(null, "Rua FLores", "300", "Ap 303", "Jardim", "5555", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "58778", cli1, c2);
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepo.saveAll(Arrays.asList(cli1));
		enderecoRepo.saveAll(Arrays.asList(e1, e2));
		
		// PEDIDOS
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepo.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepo.saveAll(Arrays.asList(pagto1, pagto2));
		
		
	}

}
