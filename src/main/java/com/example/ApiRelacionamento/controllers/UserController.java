package com.example.ApiRelacionamento.controllers;

import com.example.ApiRelacionamento.model.dto.UserDTO;
import com.example.ApiRelacionamento.model.entity.User;
import com.example.ApiRelacionamento.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            User user = userService.validateUserRegistration(userDTO);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> findByLoginAndPassword(@Valid @RequestBody UserDTO userDTO) {
        try {
           User user  = userService.validateLogin(userDTO);
            return ResponseEntity.ok(user);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



}
