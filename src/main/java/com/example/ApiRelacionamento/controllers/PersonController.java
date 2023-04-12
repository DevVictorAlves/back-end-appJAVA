package com.example.ApiRelacionamento.controllers;

import com.example.ApiRelacionamento.model.dto.PersonDTO;
import com.example.ApiRelacionamento.model.entity.Person;
import com.example.ApiRelacionamento.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {
    @Autowired
    PersonServices personServices;
    @PostMapping("/register-person")
    ResponseEntity<?> register(@Valid @RequestParam String name, @RequestParam String cpf, @RequestParam String dependentName) {
        try {
            PersonDTO personDTO = new PersonDTO();
            personDTO.setCpf(cpf);
            personDTO.setName(name);
            Person person = personServices.validatePersonRegistration(personDTO, dependentName);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
  }
    @GetMapping("/findByCpf")
    ResponseEntity<?> findByPersonCpf(@Valid @RequestParam String cpf) {
        try {
                Person person = personServices.findByPersonCpf(cpf);
                return ResponseEntity.ok(person);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete-person")
    ResponseEntity<?> deletePerson(@Valid @RequestParam("cpf") String cpf) {
        try {
            Person person = personServices.deletePerson(cpf);
            return ResponseEntity.ok(person);
        } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

