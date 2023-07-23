package com.bonjour.cursospring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonjour.cursospring.domain.Categoria;
import com.bonjour.cursospring.repositories.CategoriaRepository;
import com.bonjour.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> ret = repo.findById(id);
		return ret.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada: " + id));
		///return ret.orElse(null);
	}
	
}