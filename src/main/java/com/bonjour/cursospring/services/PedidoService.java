package com.bonjour.cursospring.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonjour.cursospring.domain.ItemPedido;
import com.bonjour.cursospring.domain.PagamentoComBoleto;
import com.bonjour.cursospring.domain.Pedido;
import com.bonjour.cursospring.domain.Produto;
import com.bonjour.cursospring.domain.enums.EstadoPagamento;
import com.bonjour.cursospring.repositories.ItemPedidoRepository;
import com.bonjour.cursospring.repositories.PagamentoRepository;
import com.bonjour.cursospring.repositories.PedidoRepository;
import com.bonjour.cursospring.repositories.ProdutoRepository;
import com.bonjour.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagtoRepo;
	
	@Autowired
	private ProdutoRepository prodRepo;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	
	@Autowired
	private ClienteService clienteService;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> ret = repo.findById(id);
		return ret.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrada: " + id));
		///return ret.orElse(null);
	}
	
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.setCliente(clienteService.buscar(pedido.getCliente().getId()));

		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagamentoComBoleto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preenchePagamentoComBoleto(pagamentoComBoleto, pedido.getInstante());
		}
		
		pedido = repo.save(pedido);
		pagtoRepo.save(pedido.getPagamento());
		
		for(ItemPedido item : pedido.getItens()) {
			Optional<Produto> produto = prodRepo.findById(item.getProduto().getId());
			item.setDesconto(0.0);
			item.setProduto(produto.get());
			item.setPreco(produto.get().getPreco());
			item.setPedido(pedido);
			itemPedidoRepo.save(item);
		}
		
		System.out.println(pedido);
		return pedido;
	}
}
