package com.bonjour.cursospring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bonjour.cursospring.domain.Categoria;
import com.bonjour.cursospring.domain.Produto;
import com.bonjour.cursospring.repositories.CategoriaRepository;
import com.bonjour.cursospring.repositories.ProdutoRepository;
import com.bonjour.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	public Produto find(Integer id) {
		Optional<Produto> ret = repo.findById(id);
		return ret.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado: " + id));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pg = PageRequest.of(page, linesPerPage, Direction.valueOf(direction) , orderBy);
		List<Categoria> categorias = categoriaRepo.findAllById(ids);
		
		return repo.search(nome, categorias, pg);
	}
}
