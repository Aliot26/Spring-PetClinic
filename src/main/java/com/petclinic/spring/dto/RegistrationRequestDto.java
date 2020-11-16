package com.petclinic.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/*
 *Created by olga on 06.11.2020
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequestDto {
    private String username;
    private String email;
    private  String password;
    private List<String> roles;
}
