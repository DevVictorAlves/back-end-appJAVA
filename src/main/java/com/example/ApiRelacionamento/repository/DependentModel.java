package com.example.ApiRelacionamento.repository;

import com.example.ApiRelacionamento.model.entity.Dependent;
import com.example.ApiRelacionamento.model.entity.Person;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DependentModel extends org.springframework.data.repository.Repository<Dependent, Integer> {
    Dependent save(Dependent dependent);
    Optional<Dependent> findByCpf(String cpf);

    List<Dependent> findAll();
    Optional<Dependent> findByName(String name);
    Dependent findByNameAndCpf(String name, String cpf);
    Optional<Dependent> findById(String name);
}
