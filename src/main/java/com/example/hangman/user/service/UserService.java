package com.example.hangman.user.service;

import com.example.hangman.user.dto.CreateUserDTO;
import com.example.hangman.user.dto.UpdatePasswordDTO;
import com.example.hangman.user.dto.UpdateUsernameDTO;
import com.example.hangman.user.model.User;

import java.util.List;

public interface UserService {
    User createUser(CreateUserDTO request);

    User updateUser(User user);
    void deleteUser(Long userId);
    User getUserById(Long userId);
    List<User> getAllUsers();
    User changeUsername (Long userId, UpdateUsernameDTO request);
    User changePassword (Long userId, UpdatePasswordDTO request);
}

