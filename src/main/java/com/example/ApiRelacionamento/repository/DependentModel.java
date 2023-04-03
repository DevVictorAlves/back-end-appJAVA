package com.example.ApiRelacionamento.repository;

import com.example.ApiRelacionamento.model.entity.Dependent;

import java.util.Optional;

public interface DependentModel {
    Dependent save(Dependent dependent);
    Optional<Dependent> findByCpf(String cpf);
}
