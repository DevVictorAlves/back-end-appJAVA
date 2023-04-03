package com.example.ApiRelacionamento.model.dto;

import com.example.ApiRelacionamento.model.entity.Dependent;

import java.util.ArrayList;
import java.util.List;

public class PersonDTO {
    private Integer id;
    private String name;
    private String cpf;
    private List<Dependent> dependents = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }
}
