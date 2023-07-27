package com.bonjour.cursospring.domain.enums;

public enum TipoCliente {
	
	PESSOA_FISICA (1, "Pessoa Física"),
	PESSOA_JURIDICA (2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	private TipoCliente() {
		// TODO Auto-generated constructor stub
	}

	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) return null;
		
		for(TipoCliente x : TipoCliente.values()) {
			if(x.getCod() == cod)
				return x;
		}
		
		throw new IllegalArgumentException("Código inválido: " + cod);
	}
	
}
