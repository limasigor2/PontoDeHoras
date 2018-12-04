package com.pdh.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Test;

public class FuncionarioTests {
	
	
	@Test
	public void test_verificar_funcionario_iguais() {
		Funcionario func1 = new Funcionario("Igor", "032.354.953-08", "PO", TipoDeUsuario.administrador.getTipo(), "senha", "userName");
		Funcionario func2 = new Funcionario("Igor", "032.354.953-08", "PO", TipoDeUsuario.administrador.getTipo(), "senha", "userName");
		assertEquals(func1.toString(), func2.toString());
	}
	@Test
	public void test_verificar_funcionario_nomes_diferentes() {
		Funcionario func1 = new Funcionario("Igor", "032.354.953-08", "PO", TipoDeUsuario.administrador.getTipo(), "senha", "userName");
		Funcionario func2 = new Funcionario("Pedro", "032.354.953-08", "PO", TipoDeUsuario.administrador.getTipo(), "senha", "userName");
		assertNotEquals(func1.toString(), func2.toString());
	}
	@Test
	public void test_verificar_funcionario_senhas_diferentes() {
		Funcionario func1 = new Funcionario("Pedro", "032.354.953-08", "PO", TipoDeUsuario.administrador.getTipo(), "senha1", "userName");
		Funcionario func2 = new Funcionario("Pedro", "032.354.953-08", "PO", TipoDeUsuario.administrador.getTipo(), "senha2", "userName");
		assertNotEquals(func1.toString(), func2.toString());
	}
	@Test
	public void test_verificar_funcionario_nomes_user_name_diferentes() {
		Funcionario func1 = new Funcionario("Igor", "032.354.953-08", "PO", TipoDeUsuario.administrador.getTipo(), "senha1", "userName1");
		Funcionario func2 = new Funcionario("Igor", "032.354.953-08", "PO", TipoDeUsuario.administrador.getTipo(), "senha2", "userName2");
		assertNotEquals(func1.toString(), func2.toString());
	}
	@Test
	public void verificar_adicionar_DiaDeTrabalho() {
		Funcionario func1 = new Funcionario("Igor", "032.354.953-08", "PO", TipoDeUsuario.administrador.getTipo(), "senha1", "userName1");
		LocalTime entrada =  LocalTime.of(7, 40);
		LocalTime saidaAlmoco = LocalTime.of(11, 40);
		LocalTime entradaAlmoco = LocalTime.of(13,00);
		LocalTime saida = LocalTime.of(17,00);
		
		DiaDeTrabalho diaDeTrabalho = new DiaDeTrabalho(func1, entrada, saidaAlmoco, entradaAlmoco, saida);
		func1.adicionarDiaDeTrabalho(diaDeTrabalho);
		assertTrue(func1.getDiasDeTrabalho().contains(diaDeTrabalho));
	}
	@Test
	public void verificar_adicionar_getDiaDeTrabalhoNaoCadastrado() {
		Funcionario func1 = new Funcionario("Igor", "032.354.953-08", "PO", TipoDeUsuario.administrador.getTipo(), "senha1", "userName1");
		LocalTime entrada =  LocalTime.of(7, 40);
		LocalTime saidaAlmoco = LocalTime.of(11, 40);
		LocalTime entradaAlmoco = LocalTime.of(13,00);
		LocalTime saida = LocalTime.of(17,00);
		
		DiaDeTrabalho diaDeTrabalho1 = new DiaDeTrabalho(func1, entrada, saidaAlmoco, entradaAlmoco, saida);
		DiaDeTrabalho diaDeTrabalho2 = new DiaDeTrabalho();
		func1.adicionarDiaDeTrabalho(diaDeTrabalho1);
		assertFalse(func1.getDiasDeTrabalho().contains(diaDeTrabalho2));
	}
	@Test
	public void verificar_remover_DiaDeTrabalho_cadastrado() {
		Funcionario func1 = new Funcionario("Igor", "032.354.953-08", "PO", TipoDeUsuario.administrador.getTipo(), "senha1", "userName1");
		LocalTime entrada =  LocalTime.of(7, 40);
		LocalTime saidaAlmoco = LocalTime.of(11, 40);
		LocalTime entradaAlmoco = LocalTime.of(13,00);
		LocalTime saida = LocalTime.of(17,00);
		
		DiaDeTrabalho diaDeTrabalho1 = new DiaDeTrabalho(func1, entrada, saidaAlmoco, entradaAlmoco, saida);
		func1.adicionarDiaDeTrabalho(diaDeTrabalho1);
		assertTrue(func1.removerDiaDeTrabalho(diaDeTrabalho1));
		assertFalse(func1.getDiasDeTrabalho().contains(diaDeTrabalho1));
	}
	@Test
	public void verificar_remover_DiaDeTrabalho_nao_cadastrado() {
		Funcionario func1 = new Funcionario("Igor", "032.354.953-08", "PO", TipoDeUsuario.administrador.getTipo(), "senha1", "userName1");
		LocalTime entrada =  LocalTime.of(7, 40);
		LocalTime saidaAlmoco = LocalTime.of(11, 40);
		LocalTime entradaAlmoco = LocalTime.of(13,00);
		LocalTime saida = LocalTime.of(17,00);
		
		DiaDeTrabalho diaDeTrabalho1 = new DiaDeTrabalho(func1, entrada, saidaAlmoco, entradaAlmoco, saida);
		assertFalse(func1.removerDiaDeTrabalho(diaDeTrabalho1));
		assertFalse(func1.getDiasDeTrabalho().contains(diaDeTrabalho1));
	}
	
}
