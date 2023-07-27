package com.bonjour.cursospring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bonjour.cursospring.domain.Cliente;
import com.bonjour.cursospring.repositories.ClienteRepository;
import com.bonjour.cursospring.repositories.EnderecoRepository;
import com.bonjour.cursospring.services.exceptions.DataIntegrityException;
import com.bonjour.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> ret = repo.findById(id);
		return ret.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado: " + id));
		///return ret.orElse(null);
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepo.saveAll(obj.getEnderecos());
		
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente cli = buscar(obj.getId());
		updateData(cli, obj);
		return repo.save(cli);
	}

	public void delete(Integer id) {
		buscar(id);
		try {
			repo.deleteById(id);
			
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pg = PageRequest.of(page, linesPerPage, Direction.valueOf(direction) , orderBy);
		return repo.findAll(pg);
	}

	private void updateData(Cliente newCli, Cliente obj) {
		newCli.setNome(obj.getNome());
		newCli.setEmail(obj.getEmail());
	}

}
