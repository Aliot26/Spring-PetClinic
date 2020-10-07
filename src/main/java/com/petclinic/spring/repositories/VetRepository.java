package com.petclinic.spring.repositories;

import com.petclinic.spring.model.Vet;
import org.springframework.data.repository.CrudRepository;


/*
 *Created by olga on 22.04.2020
 */
public interface VetRepository extends CrudRepository<Vet, Long> {
}
