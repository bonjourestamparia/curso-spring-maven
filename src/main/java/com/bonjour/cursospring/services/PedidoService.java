package com.bonjour.cursospring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonjour.cursospring.domain.Pedido;
import com.bonjour.cursospring.repositories.PedidoRepository;
import com.bonjour.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> ret = repo.findById(id);
		return ret.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrada: " + id));
		///return ret.orElse(null);
	}
	
}
