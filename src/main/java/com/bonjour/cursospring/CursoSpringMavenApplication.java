package com.bonjour.cursospring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bonjour.cursospring.domain.Categoria;
import com.bonjour.cursospring.repositories.CategoriaRepository;

@SpringBootApplication
public class CursoSpringMavenApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringMavenApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		repo.saveAll(Arrays.asList(cat1, cat2));
		
		
	}

}
