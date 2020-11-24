package com.petclinic.spring.services;



import com.petclinic.spring.model.Owner;

import java.util.List;

/*
 *Created by olga on 15.04.2020
 */
public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
    Owner findByUserId(Long userId);

    List<Owner> findAllByLastNameLike(String lastName);
}
