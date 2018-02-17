package com.pdh.util;

import org.springframework.stereotype.Component;

import com.pdh.model.Funcionario;
import com.pdh.model.TipoDeUsuario;

@Component
public class ValidadorDePermissao{

	public boolean temPermissao(Funcionario func, String pagina) {
		if(func == null || func.equals(null))
			return false;
		if(pagina == null || pagina.equals(null))
			return false;
		System.out.println("passei antes do adm");
		if(func.getTipo().equals(TipoDeUsuario.administrador)) {
			System.out.println("passei no adm");
			return true;
		}
		if(func.getTipo().equals(TipoDeUsuario.funcionario)) {
			return funcionarioTemPermissao(func, pagina);
		}
		return false;
	}
	private boolean funcionarioTemPermissao(Funcionario func, String pagina) {
		if(pagina.contains("VerEntradasEditavel"))
			return true;
		if(pagina.contains("funcionarios/edit"))
			return true;
		return false;
	}
}
