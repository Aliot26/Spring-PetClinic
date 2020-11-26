package com.petclinic.spring.controllers;

import com.petclinic.spring.model.Vet;
import com.petclinic.spring.services.VetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/*
 *Created by olga on 31.08.2020
 */
@Api("ApiController for Vets")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class VetApiController {
    private final VetService vetService;

    @Autowired
    public VetApiController(VetService vetService) {
        this.vetService = vetService;
    }

    @ApiOperation(value = "This will get a list of vets")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/vets")
    public ResponseEntity<Set<Vet>> getVets(){
        return new ResponseEntity<Set<Vet>>(vetService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "This will get the vet")
    @GetMapping("/vets/{vet_id}")
    public ResponseEntity<Vet> getVetById(@PathVariable Long vet_id){
        return new ResponseEntity<Vet>(vetService.findById(vet_id), HttpStatus.OK);
    }

    @ApiOperation(value = "This will create new vet")
    @PostMapping("/vets")
    public ResponseEntity<Void> createVet(@RequestBody Vet vet){
        vetService.save(vet);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "This will update vet's details")
    @PatchMapping("/vets/{vet_id}")
    public ResponseEntity<Void> updateVet(@RequestBody Vet vet){
        vetService.save(vet);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation(value = "This will delete the vet")
    @DeleteMapping("/vets/{vet_id}")
    public ResponseEntity<Void> deleteVisit(@PathVariable Long vet_id){
        vetService.deleteById(vet_id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
