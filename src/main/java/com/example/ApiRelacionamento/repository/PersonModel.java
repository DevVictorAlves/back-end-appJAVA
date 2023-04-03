package com.example.ApiRelacionamento.repository;

import com.example.ApiRelacionamento.model.entity.Person;
import com.example.ApiRelacionamento.model.entity.User;
import org.springframework.data.repository.Repository;
import java.util.Optional;
@org.springframework.stereotype.Repository
public interface PersonModel extends Repository<Person, Integer> {
    Optional<User> findByCpf(String cpf);
    Person save(Person person);
}
