package com.example.hangman.user.service.impl;


import com.example.hangman.user.dto.PictureDTO;
import com.example.hangman.user.model.User;
import com.example.hangman.user.repository.UserRepository;
import com.example.hangman.user.service.ImageService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class ImageServiceImpl implements ImageService {
    private final UserRepository userRepository;

    public ImageServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User updateProfilePicture(PictureDTO image, Long id) {
        var user = userRepository.findUserById(id);
        user.setPicture(image.getPicture());
        return userRepository.save(user);
    }

    @Override
    public void deleteProfilePicture(Long id) {
        var user = userRepository.findUserById(id);
        user.setPicture(null);
        userRepository.save(user);
    }
}
