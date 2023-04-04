package com.example.ApiRelacionamento.controllers;

import com.example.ApiRelacionamento.model.dto.PersonDTO;
import com.example.ApiRelacionamento.model.entity.Person;
import com.example.ApiRelacionamento.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {
    @Autowired
    PersonServices personServices;
    @PostMapping("/register-person")
    ResponseEntity<?> register(@Valid @RequestBody PersonDTO personDTO) {{
        try {
            Person person = personServices.validatePersonRegistration(personDTO);
            return ResponseEntity.ok(person);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

  }
    @GetMapping("/consult-depedent")
    ResponseEntity<?> findAllPerson(@Valid @RequestBody PersonDTO personDTO) {
        try {
            Person person = personServices.findAllPerson(personDTO);
            return ResponseEntity.ok(person);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
