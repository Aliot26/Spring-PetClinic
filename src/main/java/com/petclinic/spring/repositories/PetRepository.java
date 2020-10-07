package com.petclinic.spring.repositories;

import com.petclinic.spring.model.Pet;
import org.springframework.data.repository.CrudRepository;


/*
 *Created by olga on 22.04.2020
 */
public interface PetRepository extends CrudRepository<Pet, Long> {
}
