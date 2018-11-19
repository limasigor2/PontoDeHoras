package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
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
//@AutoConfigureTestDatabase(replace=Replace.NONE)
public class FuncionarioServiceTest{
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@MockBean
	private FuncionarioRepository funcionarioRepository;
	 
	
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
		Mockito.doNothing().when(funcionarioRepository).delete(1);
		funcionarios.forEach(funcionario -> {
			funcionarioService.save(funcionario);			
		});
		System.out.println(funcionarioRepository.findAll());
		List<Funcionario> funcionarios = funcionarioService.findAll();
		funcionarios.forEach(funcionario -> {
			System.out.println(funcionario.toString());
		});
		
		//assertThat()
	}
}
