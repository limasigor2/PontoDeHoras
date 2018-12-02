package com.pdh.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.pdh.PontoDeHorasApplication;
import com.pdh.model.Funcionario;
import com.pdh.model.TipoDeUsuario;
import com.pdh.repository.FuncionarioRepository;
import com.pdh.service.FuncionarioService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = PontoDeHorasApplication.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)
@DataJpaTest
//@AutoConfigureTestDatabase(replace=Replace.NONE)
public class FuncionarioServiceTest{
	
	@Autowired
	@InjectMocks
	private FuncionarioService funcionarioService;
	
	@MockBean
	private FuncionarioRepository funcionarioRepository;
	 
	@Autowired
	private TestEntityManager entityManager;
	
	private ArrayList<Funcionario> funcionarios;
	
	@Before
	public void setup() {
		funcionarios = new ArrayList<>();
		funcionarios.add(new Funcionario("Igor", "123.456.789.01", "PO", TipoDeUsuario.funcionario.getTipo(), "senha123", "igor"));
		funcionarios.add(new Funcionario("Erika", "123.456.789.01", "PO", TipoDeUsuario.funcionario.getTipo(), "senha123", "erika"));
		funcionarios.add(new Funcionario("Cezar", "123.456.789.01", "PO", TipoDeUsuario.funcionario.getTipo(), "senha123", "cezar"));
	}
	
	@Test
	public void test_success_findOne() {
		Mockito.when(funcionarioRepository.findOne(1)).thenReturn(funcionarios.get(0));
		assertThat(funcionarioService.findOne(1)).isEqualTo(funcionarios.get(0));
	}
	@Test
	public void test_succes_findOne() {
		Mockito.when(funcionarioRepository.findOne(1)).thenReturn(new Funcionario("Igor", "123.456.789.01", "PO", TipoDeUsuario.funcionario.getTipo(), "senha123", "username"));
		assertThat(funcionarioService.findOne(1)).isNotEqualTo(funcionarios.get(0));
	}
	@Test
	public void test_succes_findAll() {
		Mockito.when(funcionarioRepository.findAll()).thenReturn(funcionarios);
		assertThat(funcionarioService.findAll()).isEqualTo(funcionarios);
	}
	@Test
	public void test_fail_return_list_with_elements_findAll() {
		Mockito.when(funcionarioRepository.findAll()).thenReturn(funcionarios);
		assertThat(funcionarioService.findAll()).isNotEqualTo(new ArrayList<Funcionario>());
	}
	@Test
	public void test_fail_return_list_no_elements_findAll() {
		Mockito.when(funcionarioRepository.findAll()).thenReturn(new ArrayList<Funcionario>());
		assertThat(funcionarioService.findAll()).isNotEqualTo(funcionarios);
	}
	
	
	@Test
	public void test_delete_with_id() {
		funcionarioRepository = mock(FuncionarioRepository.class); 
		Mockito.when(funcionarioRepository.findAll()).thenReturn(funcionarios);
		
		doAnswer(Answer -> {
		        funcionarios.remove(0);
		        return null;
		}).when(funcionarioRepository).delete(0);
		
		funcionarioService = mock(FuncionarioService.class);
		Mockito.when(funcionarioService.findAll()).thenReturn(funcionarios);

		doAnswer(Answer -> {
		    	funcionarioRepository.delete(0);
		        return null;
		}).when(funcionarioService).delete(0);

		funcionarioService.delete(0);
		assertEquals(funcionarioService.findAll().size(),2);
	}
	
	@Test
	public void test_delete_with_object() {
		funcionarioRepository = mock(FuncionarioRepository.class); 
		Mockito.when(funcionarioRepository.findAll()).thenReturn(funcionarios);
		doAnswer(Answer -> {
		        funcionarios.remove(funcionarios.get(0));
		        return null;
		}).when(funcionarioRepository).delete(funcionarios.get(0));
		
		funcionarioService = mock(FuncionarioService.class);
		Mockito.when(funcionarioService.findAll()).thenReturn(funcionarios);

		doAnswer(Answer -> {
		    	funcionarioRepository.delete(funcionarios.get(0));
		        return null;

		}).when(funcionarioService).delete(funcionarios.get(0));

		funcionarioService.delete(funcionarios.get(0));
		assertThat(!funcionarioService.findAll().contains(funcionarios.get(0)));
	}
	
	@Test
	public void test_success_save() {
		funcionarioRepository = mock(FuncionarioRepository.class);
		Mockito.when(funcionarioRepository.findAll()).thenReturn(funcionarios);
		
		Funcionario diego = new Funcionario("Diego", "123.456.789.90", "PO", TipoDeUsuario.administrador.getTipo(), "senha123", "userName");
		
		doAnswer(Answer -> {
				funcionarios.add(diego);
				return null;
		}).when(funcionarioRepository).save(diego);
		
		
		funcionarioService = mock(FuncionarioService.class);
		Mockito.when(funcionarioService.findAll()).thenReturn(funcionarios);
		doAnswer(Answer -> {
				funcionarioRepository.save(diego);
				return null;
		}).when(funcionarioService).save(diego);
		
		assertEquals(3, funcionarioService.findAll().size());
		funcionarioService.save(diego);
		verify(funcionarioService, times(1)).save(diego);
		verify(funcionarioRepository, times(1)).save(diego);
		assertEquals(4, funcionarioService.findAll().size());
	}
	@Test
	public void test_fails_save() {
		funcionarioRepository = mock(FuncionarioRepository.class);
		Mockito.when(funcionarioRepository.findAll()).thenReturn(funcionarios);
		
		Funcionario diego = new Funcionario("Diego", "123.456.789.90", "PO", TipoDeUsuario.administrador.getTipo(), "senha123", "igor");
		
		doAnswer(Answer -> {
				funcionarios.add(diego);
				return null;
		}).when(funcionarioRepository).save(diego);
		
		
		funcionarioService = mock(FuncionarioService.class);
		Mockito.when(funcionarioService.findAll()).thenReturn(funcionarios);
		doAnswer(Answer -> {
				funcionarioRepository.save(diego);
				return null;
		}).when(funcionarioService).save(diego);
		
		assertEquals(3, funcionarioService.findAll().size());
		funcionarioService.save(diego);
		verify(funcionarioService, times(1)).save(diego);
		verify(funcionarioRepository, times(1)).save(diego);
		assertEquals(4, funcionarioService.findAll().size());
	}
	@Test
	public void save() {
		entityManager.persist(funcionarios.get(0));
		entityManager.flush();
		entityManager.persist(funcionarios.get(0));
		entityManager.flush();
		System.out.println(funcionarioRepository.findAll().size());
	}
	
}