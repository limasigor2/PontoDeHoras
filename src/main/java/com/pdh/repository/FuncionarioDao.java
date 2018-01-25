package com.pdh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pdh.model.Funcionario;


@Transactional
@Repository
public interface FuncionarioDao extends JpaRepository<Funcionario, Integer>{

}
