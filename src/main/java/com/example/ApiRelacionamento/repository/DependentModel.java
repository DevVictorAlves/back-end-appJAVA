package com.example.ApiRelacionamento.repository;

import com.example.ApiRelacionamento.model.entity.Dependent;

import java.util.List;
import java.util.Optional;

public interface DependentModel {
    Dependent save(Dependent dependent);
    Optional<Dependent> findByDependentCpf(String cpf);
    Optional<Dependent> findAllDependent(Dependent dependent);
    List<Dependent> findListDependent(List list);
    Optional<Dependent> findByDependentName(String name);
    Dependent findByNameAndCpf(String name, String cpf);
}
