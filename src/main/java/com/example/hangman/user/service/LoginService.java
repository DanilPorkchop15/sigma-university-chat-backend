package com.example.hangman.user.service;

import com.example.hangman.user.dto.LoginDTO;
import com.example.hangman.user.model.User;

public interface LoginService  {
    User login (LoginDTO request);
}
