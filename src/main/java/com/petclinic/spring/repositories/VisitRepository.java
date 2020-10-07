package com.petclinic.spring.repositories;

import com.petclinic.spring.model.Visit;
import org.springframework.data.repository.CrudRepository;


/*
 *Created by olga on 22.04.2020
 */
public interface VisitRepository extends CrudRepository<Visit, Long> {
}
