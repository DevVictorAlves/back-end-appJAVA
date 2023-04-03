package com.example.ApiRelacionamento.services;

import com.example.ApiRelacionamento.model.dto.DependentDTO;
import com.example.ApiRelacionamento.model.entity.Dependent;
import com.example.ApiRelacionamento.repository.DependentModel;
import org.springframework.beans.factory.annotation.Autowired;

import static com.example.ApiRelacionamento.services.utils.Util.validarDataNascimento;

public class DependentService {
    @Autowired
    DependentModel dependentModel;

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

        return dependentModel.save(dependent);
    }
}
