package com.bonjour.cursospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bonjour.cursospring.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	
	/**
	 * Ao criar um método findBy<Nome do atributo>, o Spring implementa automaticamente o método
	 * @param email
	 * @return
	 */
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
}
