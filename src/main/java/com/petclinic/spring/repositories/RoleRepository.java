package com.petclinic.spring.repositories;

import com.petclinic.spring.model.Role;
import org.springframework.data.repository.CrudRepository;


/*
 *Created by olga on 11.09.2020
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
