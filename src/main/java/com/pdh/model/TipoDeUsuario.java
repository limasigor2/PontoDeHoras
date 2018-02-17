package com.pdh.model;

public enum TipoDeUsuario {
 	funcionario("funcionario"),
 	administrador("administrador");
	
 	private final String tipo;

  	TipoDeUsuario(String tipo) {
  		this.tipo = tipo;
  	}
 	public String getTipo() {
 		return tipo;
 	}

}
