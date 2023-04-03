package com.example.ApiRelacionamento.services;

import com.example.ApiRelacionamento.model.dto.PersonDTO;
import com.example.ApiRelacionamento.model.entity.Person;
import com.example.ApiRelacionamento.repository.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServices {
    @Autowired
    private PersonModel personModel;

    public Person validateUserRegistration(PersonDTO personDTO) throws Exception {
        //iniciando validação vitovisk
        if (personModel.findByCpf(personDTO.getCpf()).isPresent()) {
            throw new Exception("CPF já foi cadastrado");
        }

        if (personDTO.getName() == null || personDTO.getName().isEmpty() || personDTO.getName().isBlank()) {
            throw new Exception("Senha deve ter pelo menos 6 caracteres");
        }
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setCpf(personDTO.getCpf());
        person.setDependents(personDTO.getDependents());
        return personModel.save(person);
    }
}
