package com.example.ApiRelacionamento.services;

import com.example.ApiRelacionamento.model.dto.DependentDTO;
import com.example.ApiRelacionamento.model.dto.PersonDTO;
import com.example.ApiRelacionamento.model.entity.Dependent;
import com.example.ApiRelacionamento.model.entity.Person;
import com.example.ApiRelacionamento.repository.DependentModel;
import com.example.ApiRelacionamento.repository.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServices {
    @Autowired
    private PersonModel personModel;
    @Autowired
    private DependentModel dependentModel;

    public Person validatePersonRegistration(PersonDTO personDTO, String dependentName) throws Exception {
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

        Dependent dependent = new Dependent();
        dependent.setName(dependentName);
        Optional<Dependent> found = dependentModel.findByName(dependent.getName());
        List<Dependent> dependents = new ArrayList<>();
        dependents.add(found.get());

        person.setDependents(dependents);

        Dependent dependentUpdate = new Dependent();
        dependentUpdate = found.get();
        dependentUpdate.setPerson(person);
        personModel.save(person);

        if(!dependentModel.existsByPersonId(dependentUpdate.getPerson().getId())) {
            dependentModel.save(dependentUpdate);
            return person;
        } else {
            throw new Exception("Id já vinculado");
        }
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
    public Person findByPersonCpf(String cpf) throws Exception {
        if (personModel.findByCpf(cpf).isPresent()) {
            Person person = new Person();
            person.setCpf(cpf);
            personModel.findByCpf(person.getCpf());
            return person;
        } else {
            throw new Exception("Não foi encontrado pessoa no banco de dados");
        }
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
        Optional<Person> personOptional = personModel.findByCpf(cpf);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            personModel.delete(person);
        } else {
            throw new Exception("Pessoa não encontrada com o CPF informado");
        }
        return new Person();
    }
    public Integer findPersonIdByCpf(String cpf) throws Exception {
        Optional<Person> personOptional = personModel.findByCpf(cpf);
        if (personOptional.isPresent()) {
            return personOptional.get().getId();
        }
        throw new Exception("Person not found for CPF: " + cpf);
    }
}
