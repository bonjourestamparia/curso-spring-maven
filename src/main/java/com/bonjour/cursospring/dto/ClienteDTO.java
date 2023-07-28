package com.bonjour.cursospring.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.bonjour.cursospring.domain.Cliente;
import com.bonjour.cursospring.services.validation.ClienteUpdate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@ClienteUpdate
public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	@NotEmpty(message = "O nome deve ser informado")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caractéres")
	private String nome;
	
	@NotEmpty(message = "O email deve ser informado")
	@Email(message = "Email inválido")
	private String email;

	public ClienteDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ClienteDTO(Integer id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Cliente toCliente() {
		return new Cliente(id, nome, email, null, null);
	}
}
