package com.bonjour.cursospring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonjour.cursospring.domain.Categoria;
import com.bonjour.cursospring.domain.Cliente;
import com.bonjour.cursospring.repositories.ClienteRepository;
import com.bonjour.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> ret = repo.findById(id);
		return ret.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado: " + id));
		///return ret.orElse(null);
	}
	
}
