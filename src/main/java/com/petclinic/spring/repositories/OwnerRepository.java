package com.petclinic.spring.repositories;

import com.petclinic.spring.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/*
 *Created by olga on 22.04.2020
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName);
    List<Owner> findAllByLastNameLike(String lastName);
}
