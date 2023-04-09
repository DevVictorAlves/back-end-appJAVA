package com.example.ApiRelacionamento.repository;

import com.example.ApiRelacionamento.model.entity.Person;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
@org.springframework.stereotype.Repository
public interface PersonModel extends Repository<Person, Integer> {
    Optional<Person> findByCpf(String cpf);
    Person save(Person person);
    List<Person> findAll();
    Person delete(Person person);
    Optional<Person> findByName(String name);
}
