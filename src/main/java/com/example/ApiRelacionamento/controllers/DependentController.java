package com.example.ApiRelacionamento.controllers;

import com.example.ApiRelacionamento.model.dto.DependentDTO;
import com.example.ApiRelacionamento.model.dto.PersonDTO;
import com.example.ApiRelacionamento.model.entity.Dependent;
import com.example.ApiRelacionamento.model.entity.Person;
import com.example.ApiRelacionamento.services.DependentService;
import com.example.ApiRelacionamento.services.PersonServices;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/dependent")
public class DependentController {
    @Autowired
    DependentService dependentService;
    @Autowired
    PersonServices personServices;


    @PostMapping("/register-dependent")
    ResponseEntity<?> registerDependent(@Valid @RequestBody DependentDTO dependentDTO) {
        try {
            Dependent dependent = dependentService.validateDependentRegistration(dependentDTO);
            return ResponseEntity.ok(dependentDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

}
    @GetMapping("/consul-dependent")
    ResponseEntity<?> findAllDependent(@Valid @RequestBody DependentDTO dependentDTO) {
        try {
            if(dependentDTO.getName() == null && dependentDTO.getName().isEmpty()
                    || dependentDTO.getCpf() == null || dependentDTO.getCpf().isEmpty()) {
                List<Dependent> dependent = new ArrayList<>();
                dependent = dependentService.findAllDependent(dependent);
                return ResponseEntity.ok(dependent);
            } else {
            Dependent dependent = dependentService.findAllDependent(dependentDTO);
            return ResponseEntity.ok(dependentDTO);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/consult-dependent-name")
    ResponseEntity<?> findByNameDependent(@Valid @RequestBody DependentDTO dependentDTO) {
        try {
            if(dependentDTO.getName() != null || !dependentDTO.getName().isEmpty()) {
                List<DependentDTO> dependentDTOS = new ArrayList<>();
                Dependent dependent = dependentService.findByNameDependent(dependentDTO);
                return ResponseEntity.ok(dependent.getName());
            } else
            { throw new Exception("Nome de dependente inválido"); }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/bond")
    public ResponseEntity<?> bondDependent(@Valid @RequestParam String name, @RequestParam String cpf) {
        try {
            Dependent BondDependent = dependentService.bondDependentToPerson(cpf, name);
            return ResponseEntity.ok(BondDependent);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


