package com.pdh.repository;


import org.junit.Assert;


//import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pdh.PontoDeHorasApplication;
import com.pdh.model.Funcionario;
import com.pdh.repository.FuncionarioRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@SpringBootTest(classes = PontoDeHorasApplication.class)
public class FuncionarioRepostoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Test
	public void testFindByUserName_AssertTrue(){
		Funcionario funcionario = new Funcionario("Igor Lima", "123.456.789.59", "123.456.789.58", "administrador", "senha", "username");
		entityManager.persist(funcionario);
		entityManager.flush();
		
		Funcionario retornado = funcionarioRepository.findByUserName("username");
		
		Assert.assertTrue(retornado.getUserName().equals(funcionario.getUserName()));	
	}
	
	@Test
	public void testFindByUserName_AssertFalse() {
		Funcionario funcionario = new Funcionario("Igor Lima", "123.456.789.59", "123.456.789.58", "administrador", "senha", "username");
		entityManager.persist(funcionario);
		entityManager.flush();
		
		Funcionario retornado = funcionarioRepository.findByUserName("UserName");

		Assert.assertNull(retornado);
	}
	@Test
	public void save() {
		Funcionario igor = new Funcionario("Igor Lima", "123.456.789.59", "123.456.789.58", "administrador", "senha", "username");

		entityManager.persist(igor);
		entityManager.flush();

	}
	
}
