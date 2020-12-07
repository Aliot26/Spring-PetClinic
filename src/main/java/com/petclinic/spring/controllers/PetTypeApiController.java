package com.petclinic.spring.controllers;

import com.petclinic.spring.model.PetType;
import com.petclinic.spring.services.PetTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/*
 *Created by olga on 31.08.2020
 */
@Api("ApiController for PetTypes")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class PetTypeApiController {
    private final PetTypeService petTypeService;

    public PetTypeApiController(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @ApiOperation(value = "This will get list of petTypes")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/petTypes")
    public ResponseEntity<Set<PetType>> getPetTypes(){
        return new ResponseEntity<Set<PetType>>(petTypeService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "This will get the petType")
    @GetMapping("/petTypes/{petType_id}")
    public ResponseEntity<PetType> getPetTypeById(@PathVariable Long petType_id){
        return new ResponseEntity<PetType>(petTypeService.findById(petType_id), HttpStatus.OK);
    }

    @ApiOperation(value = "This will create new petType")
    @PostMapping("/petTypes")
    public ResponseEntity<Void> createPetType(@RequestBody PetType petType){
        petTypeService.save(petType);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "This will update petType's details")
    @PatchMapping("/petTypes/{petType_id}")
    public ResponseEntity<Void> updatePetType(@RequestBody PetType petType){
        petTypeService.save(petType);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation(value = "This will delete the petType")
    @DeleteMapping("/petTypes/{petType_id}")
    public ResponseEntity<Void> deletePetType(@PathVariable Long petType_id){
        petTypeService.deleteById(petType_id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
