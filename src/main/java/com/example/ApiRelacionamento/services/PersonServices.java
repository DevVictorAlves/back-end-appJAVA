package com.example.ApiRelacionamento.services;

import com.example.ApiRelacionamento.model.dto.DependentDTO;
import com.example.ApiRelacionamento.model.dto.PersonDTO;
import com.example.ApiRelacionamento.model.entity.Dependent;
import com.example.ApiRelacionamento.model.entity.Person;
import com.example.ApiRelacionamento.repository.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Person findByPerson(PersonDTO personDTO) throws Exception {
        if (personModel.findByCpf(personDTO.getCpf()).isPresent()) {
            Person person = new Person();
            person.setCpf(personDTO.getCpf());
            personModel.findByCpf(person.getCpf());
            return person;
        }
        if (personModel.findByName(personDTO.getName()).isPresent()) {
            Person person = new Person();
            person.setName(personDTO.getName());
            personModel.findByName(person.getName());
            return person;
        }
        return new Person();

    }
    public List<Person> findAllPerson (List persons) {
        //iniciando busca de filtros de pessoas no banco
        if (persons.isEmpty()) {
            List<Person> person = new ArrayList<>();
            person = personModel.findAll();
            return person;
        }
        return new ArrayList<>();
    }
    public Person deletePerson (String cpf) throws Exception {
        //tentativa de deleta pessoa por cpf, vamos ver o q dar né
        if(cpf != null || !cpf.isEmpty()) {
            Person person = new Person();
            person.setCpf(cpf);
            personModel.findByCpf(person.getCpf());
            personModel.delete(person);
            return person;
        }
        else {
            throw new Exception("Ocorreu um erro ao delete pessoa");
        }
    }
    public Integer findPersonIdByCpf(String cpf) throws Exception {
        Optional<Person> personOptional = personModel.findByCpf(cpf);
        if (personOptional.isPresent()) {
            return personOptional.get().getId();
        }
        throw new Exception("Person not found for CPF: " + cpf);
    }
}
