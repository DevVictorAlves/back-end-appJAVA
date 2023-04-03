package com.example.ApiRelacionamento.controllers;

import com.example.ApiRelacionamento.model.dto.PersonDTO;
import com.example.ApiRelacionamento.model.entity.Person;
import com.example.ApiRelacionamento.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {
    @Autowired
    PersonServices personServices;
    @PostMapping(name = "/register")
    ResponseEntity<?> register(@Valid @RequestBody PersonDTO personDTO) {{
        try {
            Person person = personServices.validateUserRegistration(personDTO);
            return ResponseEntity.ok(person);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
}
