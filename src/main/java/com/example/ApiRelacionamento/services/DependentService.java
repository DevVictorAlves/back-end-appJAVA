package com.example.ApiRelacionamento.services;

import com.example.ApiRelacionamento.model.dto.DependentDTO;
import com.example.ApiRelacionamento.model.entity.Dependent;
import com.example.ApiRelacionamento.repository.DependentModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static com.example.ApiRelacionamento.services.utils.Util.*;

public class DependentService {
    @Autowired
    DependentModel dependentModel;

    public Dependent validateDependentRegistration(DependentDTO dependentDTO) throws Exception {
        //iniciando validação vitovisk
        if (dependentModel.findByDependentCpf(dependentDTO.getCpf()).isPresent()) {
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
        if(dependentModel.findByDependentCpf(dependentDTO.getCpf()).isPresent() && dependentModel.findByDependentName(dependentDTO.getName()).isPresent()) {
            Dependent dependent = new Dependent();
            dependent.setName(dependentDTO.getName());
            dependent.setCpf(dependentDTO.getCpf());
            return dependentModel.findByNameAndCpf(dependent.getName(), dependent.getCpf());

        }
        if(dependentModel.findByDependentCpf(dependentDTO.getCpf()).isPresent()) {
            Dependent dependent = new Dependent();
            dependent.setCpf(dependentDTO.getCpf());
            dependentModel.findByDependentCpf(dependent.getCpf());
            return dependent;
        }
        if(dependentModel.findByDependentName(dependentDTO.getName()).isPresent()) {
            Dependent dependent = new Dependent();
            dependent.setCpf(dependentDTO.getName());
            dependentModel.findByDependentName(dependent.getName());
            return dependent;
    }
        return new Dependent();
    }
    public List<DependentDTO> findAllDependent(List dependent) throws Exception {
        if(dependent.isEmpty()){
        dependentModel.findListDependent(dependent);
        return dependent;
        } else {
            return new ArrayList<>();
        }
    }
    public Dependent findByNameDependent(DependentDTO dependentDTO) throws Exception {
        if (!dependentDTO.getName().isEmpty() || dependentDTO.getName() != null) {
        Dependent dependent = new Dependent();
        dependent.setName(dependentDTO.getName());
        dependentModel.findForIdByName(dependent.getName());
         return dependent;
        } else
        { throw new Exception("Erro ao consultar, nome do dependente está vazio para consulta"); }
}
}
