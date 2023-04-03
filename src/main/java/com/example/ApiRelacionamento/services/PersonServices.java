package com.example.ApiRelacionamento.services;

import com.example.ApiRelacionamento.model.dto.DependentDTO;
import com.example.ApiRelacionamento.model.dto.PersonDTO;
import com.example.ApiRelacionamento.model.entity.Dependent;
import com.example.ApiRelacionamento.model.entity.Person;
import com.example.ApiRelacionamento.repository.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServices {
    @Autowired
    private PersonModel personModel;

    public Person validatePersonRegistration(PersonDTO personDTO) throws Exception {
        //iniciando validação vitovisk
        if (personModel.findByCpf(personDTO.getCpf()).isPresent()) {
            throw new Exception("CPF já foi cadastrado");
        }

        if (personDTO.getName() == null || personDTO.getName().isEmpty()) {
            throw new Exception("O nome não pode ser vazio");
        }
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setCpf(personDTO.getCpf());
        List<Dependent> dependents = new ArrayList<>();
        for (DependentDTO dependentDTO : personDTO.getDependents()) {
            if (dependentDTO.getName() == null || dependentDTO.getName().isEmpty()) {
                throw new Exception("O nome do dependente não pode ser vazio");
            }

            Dependent dependent = new Dependent();
            dependent.setName(dependentDTO.getName());
            dependent.setBirth(dependentDTO.getBirth());
            dependent.setPerson(person);
            dependents.add(dependent);
        }

        person.setDependents(dependents);

        return personModel.save(person);
    }

    public Person findAllPerson(PersonDTO personDTO) throws Exception {
        //iniciando busca de filtros de pessoas no banco
        if (personModel.findByCpf(personDTO.getCpf()).isPresent() && personModel.findByName(personDTO.getName()).isPresent()) {
            Person person = new Person();
            personModel.findAllPerson(person);
            return person;
        }
        if (personModel.findByCpf(personDTO.getCpf()).isPresent()) {
            Person person = new Person();
            person.setCpf(personDTO.getCpf());
            personModel.findByPersonCpf(person.getCpf());
            return person;
        }
        if (personModel.findByName(personDTO.getName()).isPresent()) {
            Person person = new Person();
            person.setName(personDTO.getName());
            personModel.findByPersonName(person.getName());
            return person;
        }
        return new Person();

    }
}
