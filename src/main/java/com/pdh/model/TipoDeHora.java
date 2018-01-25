package com.pdh.model;

public enum TipoDeHora {
	ENTRADA_MANHA("EntradaManha"),
	SAIDA_MANHA("SaidaManha"),
	ENTRADA_TARDE("EntradaTarde"),
	SAIDA_TARDE("SaidaTarde"),
	ENTRADA_NOITE("Entrada_Noite"), 
	SAIDA_NOITE("Saida_Noite");
	private final String tipo;
	
	TipoDeHora(String tipo) {
		this.tipo = tipo;
	}
	public String getTipo() {
		return tipo;
	}
}
