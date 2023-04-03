package com.example.ApiRelacionamento.repository;

import com.example.ApiRelacionamento.model.entity.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;
@org.springframework.stereotype.Repository
public interface UserModel extends Repository<User, Integer> {
    Optional<User> findByLogin(String login);
    User save(User user);
    User findByLoginAndPassword(String login, String password);

}
