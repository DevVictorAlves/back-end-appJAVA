package com.example.ApiRelacionamento.repository;

import com.example.ApiRelacionamento.model.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
@org.springframework.stereotype.Repository
public interface PersonModel extends Repository<Person, Integer> {
    Optional<Person> findByCpf(String cpf);
    @Query("SELECT p.id FROM Person p WHERE p.cpf = :cpf")
    Integer findIdByCpf(@Param("cpf") String cpf);

    Person save(Person person);
    List<Person> findAll();
    Person delete(Person person);
    Optional<Person> findByName(String name);
}
