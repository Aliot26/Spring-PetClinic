package com.petclinic.spring.services;


import com.petclinic.spring.model.ERole;
import com.petclinic.spring.model.Role;

/*
 *Created by olga on 11.09.2020
 */
public interface RoleService extends CrudService<Role, Long>  {
    Role findByName(ERole name);
}
