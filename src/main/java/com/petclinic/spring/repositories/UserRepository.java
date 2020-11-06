package com.petclinic.spring.repositories;

import com.petclinic.spring.model.User;
import org.springframework.data.repository.CrudRepository;


/*
 *Created by olga on 13.09.2020
 */
public interface UserRepository  extends CrudRepository<User, Long> {
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
