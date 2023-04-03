package com.example.ApiRelacionamento.services;

import com.example.ApiRelacionamento.repository.UserModel;
import com.example.ApiRelacionamento.model.dto.UserDTO;
import com.example.ApiRelacionamento.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserModel userModel;

    public User validateUserRegistration(UserDTO userDTO) throws Exception {
        //iniciando validação vitovisk
        if (userModel.findByLogin(userDTO.getLogin()).isPresent()) {
            throw new Exception("Email já existe");
        }

        if (userDTO.getPassword() == null || userDTO.getPassword().length() < 6) {
            throw new Exception("Senha deve ter pelo menos 6 caracteres");
        }
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        return userModel.save(user);
    }

    public User validateLogin(UserDTO userDTO) throws RuntimeException {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());

        User found = userModel.findByLoginAndPassword(user.getLogin(), user.getPassword());

        if (found == null || found.getLogin().isEmpty() || found.getPassword().isEmpty()) {
            throw new RuntimeException("Usuário ou senha incorretos");
        }

        return user;
    }
}
