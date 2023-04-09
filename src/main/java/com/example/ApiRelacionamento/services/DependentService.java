package com.example.ApiRelacionamento.services;

import com.example.ApiRelacionamento.model.dto.DependentDTO;
import com.example.ApiRelacionamento.model.entity.Dependent;
import com.example.ApiRelacionamento.model.entity.Person;
import com.example.ApiRelacionamento.repository.DependentModel;
import com.example.ApiRelacionamento.repository.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.ApiRelacionamento.services.utils.Util.*;
@Service
public class DependentService {
    @Autowired
    DependentModel dependentModel;
    @Autowired
    PersonModel personModel;

    public Dependent validateDependentRegistration(DependentDTO dependentDTO) throws Exception {
        //iniciando validação vitovisk
        if (dependentModel.findByCpf(dependentDTO.getCpf()).isPresent()) {
            throw new Exception("CPF já foi cadastrado");
        }
        if (dependentDTO.getName() == null || dependentDTO.getName().isEmpty()) {
            throw new Exception("O nome não pode ser vazio");
        }
        if(!validarDataNascimento(dependentDTO.getBirth())) {
            throw new Exception("A data de nascimento é necessária");
        }
        Dependent dependent = new Dependent();
        dependent.setName(dependentDTO.getName());
        dependent.setCpf(dependentDTO.getCpf());
        dependent.setBirth(dependentDTO.getBirth());
        dependent.setBirth(dependentDTO.getBirth());

        return dependentModel.save(dependent);
    }

    public Dependent findAllDependent(DependentDTO dependentDTO) throws Exception {
        //filtros para consulta de dependent --vitolas  commitsss papai
        if(dependentModel.findByCpf(dependentDTO.getCpf()).isPresent() &&
                dependentModel.findByName(dependentDTO.getName()).isPresent()) {
            Dependent dependent = new Dependent();
            dependent.setName(dependentDTO.getName());
            dependent.setCpf(dependentDTO.getCpf());
            return dependentModel.findByNameAndCpf(dependent.getName(), dependent.getCpf());

        }
        if(dependentModel.findByCpf(dependentDTO.getCpf()).isPresent()) {
            Dependent dependent = new Dependent();
            dependent.setCpf(dependentDTO.getCpf());
            dependentModel.findByCpf(dependent.getCpf());
            return dependent;
        }
        if(dependentModel.findByName(dependentDTO.getName()).isPresent()) {
            Dependent dependent = new Dependent();
            dependent.setCpf(dependentDTO.getName());
            dependentModel.findByName(dependent.getName());
            return dependent;
    }
        return new Dependent();
    }
    public List<Dependent> findAllDependent(List dependent) throws Exception {
        if(dependent.isEmpty()){
            List<Dependent> dependents = new ArrayList<>();
            dependents = dependentModel.findAll();
            return dependents;
        } else {
            return new ArrayList<>();
        }
    }
    public Dependent findByNameDependent(DependentDTO dependentDTO) throws Exception {
        if (!dependentDTO.getName().isEmpty() || dependentDTO.getName() != null) {
        Dependent dependent = new Dependent();
        dependent.setName(dependentDTO.getName());
        dependentModel.findById(dependent.getName());
         return dependent;
        } else
        { throw new Exception("Erro ao consultar, nome do dependente está vazio para consulta"); }
    }
    public Dependent bondDependentToPerson(String cpf, String name) throws Exception {
         Optional<Person> personOptional = personModel.findByCpf(cpf);
        if (!personOptional.isPresent()) {
            throw new Exception("Pessoa não encontrada com o CPF informado");
        }
        Optional<Dependent> dependentOptional = dependentModel.findByName(name);
        if(!dependentOptional.isPresent()) {
            throw new Exception("Depedente não encontrado");
        }

        Dependent getDependent = dependentOptional.get();
        Person getPerson = personOptional.get();

        Dependent dependent = new Dependent();
        dependent.setName(getDependent.getName());
        dependent.setCpf(getDependent.getCpf());
        dependent.setPerson(getPerson);

        return dependentModel.save(dependent);
    }
}

