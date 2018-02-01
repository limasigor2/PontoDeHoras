package com.pdh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pdh.model.DiaDeTrabalho;

@Transactional
@Repository
public interface DiaDeTrabalhoDao extends JpaRepository<DiaDeTrabalho, Integer>{

}
