package com.petclinic.spring.services;


import com.petclinic.spring.dto.RegistrationRequestDto;
import com.petclinic.spring.model.User;

import java.util.List;

/*
 *Created by olga on 02.09.2020
 */
public interface UserService {
    User register(RegistrationRequestDto user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);

    User findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
