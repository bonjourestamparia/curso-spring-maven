package com.bonjour.cursospring.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE (1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO (3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	private EstadoPagamento() {
		// TODO Auto-generated constructor stub
	}

	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(int cod) {
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(x.getCod() == cod)
				return x;
		}
		
		throw new IllegalArgumentException("Código inválido: " + cod);
	}
	
}
