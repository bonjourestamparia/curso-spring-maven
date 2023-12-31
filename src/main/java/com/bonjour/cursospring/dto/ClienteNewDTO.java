package com.bonjour.cursospring.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.bonjour.cursospring.domain.Cidade;
import com.bonjour.cursospring.domain.Cliente;
import com.bonjour.cursospring.domain.Endereco;
import com.bonjour.cursospring.domain.enums.TipoCliente;
import com.bonjour.cursospring.services.validation.ClienteInsert;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@ClienteInsert
public class ClienteNewDTO implements  Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "O nome deve ser informado")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caractéres")
	private String nome;
	
	@NotEmpty(message = "O email deve ser informado")
	@Email(message = "Email inválido")
	private String email;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String cpfOuCnpj;
	private Integer tipo;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String logradouro;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String numero;
	
	private String complemento;
	private String bairro;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cep;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeId;
	
	public ClienteNewDTO() {
		// TODO Auto-generated constructor stub
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	public Cliente toCliente() {
		Cliente cli = new Cliente(null, nome, email, cpfOuCnpj, TipoCliente.toEnum(tipo));
		Endereco end = new Endereco(null, logradouro, numero, complemento, bairro, cep, cli, new Cidade(cidadeId));
		
		cli.getEnderecos().add(end);
		cli.getTelefones().add(telefone1);
		
		if(telefone2 != null) cli.getTelefones().add(telefone2);
		if(telefone3 != null) cli.getTelefones().add(telefone3);
		
		return cli;
	}
}
