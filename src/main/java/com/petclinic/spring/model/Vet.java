package com.petclinic.spring.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*
 *Created by olga on 13.04.2020
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "vets")
public class Vet extends Person {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialities", joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialities = new HashSet<>();

    private String photo;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Vet(Long id, String firstName, String lastName, Set<Speciality> specialities){
        super(id, firstName, lastName);
        this.specialities = specialities;
    }

    public int getNumberSpecialities(){
        return specialities.size();
    }

}
