package com.example.hangman.user.service.impl;

import com.example.hangman.user.dto.LoginDTO;
import com.example.hangman.user.model.User;
import com.example.hangman.user.repository.UserRepository;
import com.example.hangman.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(LoginDTO request) {
        return userRepository.findUserByUsername(request.getUsername());
    }
}
