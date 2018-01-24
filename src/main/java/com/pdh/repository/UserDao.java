package com.pdh.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.pdh.model.User;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer>{

}
