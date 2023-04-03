package com.example.ApiRelacionamento.controllers;

import com.example.ApiRelacionamento.model.dto.DependentDTO;
import com.example.ApiRelacionamento.model.dto.PersonDTO;
import com.example.ApiRelacionamento.model.entity.Dependent;
import com.example.ApiRelacionamento.model.entity.Person;
import com.example.ApiRelacionamento.services.DependentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/dependent")
public class DependentController {
    @Autowired
    DependentService dependentService;


    @PostMapping("/register-dependent")
    ResponseEntity<?> registerDependent(@Valid @RequestBody DependentDTO dependentDTO) {{
        try {
            Dependent dependent = dependentService.validateDependentRegistration(dependentDTO);
            return ResponseEntity.ok(dependentDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
}
