package com.petclinic.spring.controllers;

import com.petclinic.spring.model.Owner;
import com.petclinic.spring.services.OwnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/*
 *Created by olga on 11.08.2020
 */
@Api("ApiController for Owners")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class OwnerApiController{
    private final OwnerService ownerService;

    public OwnerApiController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }
    @ApiOperation(value = "This will get a list of owners", notes = "In this list we have pets")
    @GetMapping("/owners")
    public ResponseEntity<Set<Owner>> getOwners(){
        return new ResponseEntity<Set<Owner>>(ownerService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "This will get the owner")
    @GetMapping("/owners/{owner_id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long owner_id){
        return new ResponseEntity<Owner>(ownerService.findById(owner_id), HttpStatus.OK);
    }

    @ApiOperation(value = "This will get the owner by user_id")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/owners/user/{user_id}")
    public ResponseEntity<Owner> getOwnerByUsername(@PathVariable Long user_id){
        return new ResponseEntity<Owner>(ownerService.findByUserId(user_id), HttpStatus.OK);
    }

    @ApiOperation(value = "This will create new owner")
    @PostMapping("/owners")
    public ResponseEntity<Void> createOwner(@RequestBody Owner owner){
        ownerService.save(owner);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "This will update owner's details")
    @PatchMapping("/owners/{owner_id}")
    public ResponseEntity<Void> updateOwner(@RequestBody Owner owner){
        ownerService.save(owner);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ApiOperation(value = "This will delete the owner")
    @DeleteMapping("/owners/{owner_id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long owner_id){
        ownerService.deleteById(owner_id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}

