package com.petclinic.spring.model;

/*
 *Created by olga on 05.11.2020
 */
public enum ERole {
    ROLE_USER("ROLE_USER"),
    ROLE_MODERATOR("ROLE_MODERATOR"),
    ROLE_ADMIN("ROLE_ADMIN");

    private String role;

    ERole(String erole) {
        this.role = erole;
    }

    public String getRole() {
        return role;
    }
}
