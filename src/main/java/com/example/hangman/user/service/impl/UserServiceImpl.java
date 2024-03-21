package com.example.hangman.user.service.impl;

import com.example.hangman.user.dto.CreateUserDTO;
import com.example.hangman.user.dto.UpdatePasswordDTO;
import com.example.hangman.user.dto.UpdateUsernameDTO;
import com.example.hangman.game.repository.GameRepository;
import com.example.hangman.user.model.User;
import com.example.hangman.user.repository.UserRepository;
import com.example.hangman.user.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;


    @Override
    public User createUser(CreateUserDTO request) {
        if (userRepository.findUserByUsername(request.getUsername()) == null){
            var user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(request.getPassword());
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Удаляем все игры пользователя из базы данных
        gameRepository.deleteAllByUser(user);

        // Удаляем самого пользователя
        userRepository.deleteById(userId);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User changeUsername(Long userId, UpdateUsernameDTO request) {
        var user = getUserById(userId);
        user.setUsername(request.getUsername());
        return userRepository.save(user);
    }

    @Override
    public User changePassword(Long userId, UpdatePasswordDTO request) {
        var user = getUserById(userId);
        user.setPassword(request.getPassword());
        return userRepository.save(user);
    }
}
