package com.petclinic.spring.controllers;

import com.petclinic.spring.model.Pet;
import com.petclinic.spring.services.PetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/*
 *Created by olga on 20.08.2020
 */
@Api("ApiController for Pets")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class PetApiController {
    private final PetService petService;

    public PetApiController(PetService petService) {
        this.petService = petService;
    }

    @ApiOperation(value = "This will get list of pets")
    @GetMapping("/pets")
    public ResponseEntity<Set<Pet>> getPets(){
        return new ResponseEntity<Set<Pet>>(petService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "This will get the pet")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/pets/{pet_id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long pet_id){
        return new ResponseEntity<Pet>(petService.findById(pet_id), HttpStatus.OK);
    }

    @ApiOperation(value = "This will create new pet")
    @PostMapping("/pets")
    public ResponseEntity<Void> createPet(@RequestBody Pet pet){
        petService.save(pet);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "This will update pet's details")
    @PatchMapping("/pets/{pet_id}")
    public ResponseEntity<Void> updatePet(@RequestBody Pet pet){
        petService.save(pet);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation(value = "This will delete the pet")
    @DeleteMapping("/pets/{pet_id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long pet_id){
        petService.deleteById(pet_id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
