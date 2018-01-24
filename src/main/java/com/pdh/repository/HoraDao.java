package com.pdh.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pdh.model.Hora;

@Transactional
@Repository
public interface HoraDao extends CrudRepository<Hora, Integer>{

}
