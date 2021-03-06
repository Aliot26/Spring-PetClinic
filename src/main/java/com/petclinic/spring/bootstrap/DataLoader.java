package com.petclinic.spring.bootstrap;

import com.petclinic.spring.model.ERole;
import com.petclinic.spring.model.PetType;
import com.petclinic.spring.model.Role;
import com.petclinic.spring.model.Speciality;
import com.petclinic.spring.services.PetTypeService;
import com.petclinic.spring.services.RoleService;
import com.petclinic.spring.services.SpecialityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/*
 *Created by olga on 16.04.2020
 */
@Component
public class DataLoader implements CommandLineRunner {
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final RoleService roleService;

    public DataLoader(PetTypeService petTypeService, SpecialityService specialityService, RoleService roleService) {

        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.roleService = roleService;
    }


    @Override
    public void run(String... args) throws Exception {
        int countTypes = petTypeService.findAll().size();
        if (countTypes == 0) {
            loadTypes();
        }

        int countSpecialities = specialityService.findAll().size();
        if (countSpecialities == 0) {
            loadSpecialities();
        }

        int countRoles = roleService.findAll().size();
        if(countRoles == 0){
            loadRoles();
        }
    }

    private void loadSpecialities() {
        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);


    }

    private void loadTypes() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);
    }

    private void loadRoles(){
        Role admin = new Role();
        admin.setName(ERole.ROLE_ADMIN);
        Role savedAdminRole = roleService.save(admin);

        Role moderator = new Role();
        moderator.setName(ERole.ROLE_MODERATOR);
        Role saveModerRole = roleService.save(moderator);

        Role user = new Role();
        user.setName(ERole.ROLE_USER);
        Role savedUserRole = roleService.save(user);
    }
}
