package com.example.hangman.user.service.impl;

import com.example.hangman.user.dto.RatingUserDTO;
import com.example.hangman.user.dto.UpdateRatingDTO;
import com.example.hangman.user.model.User;
import com.example.hangman.user.repository.UserRepository;
import com.example.hangman.user.service.RatingService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    private final UserRepository userRepository;

    public RatingServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User update(UpdateRatingDTO request, Long id) {
        User user = userRepository.findUserById(id);
        user.setRating(request.getRating());
        return userRepository.save(user);
    }

    @Override
    public List<RatingUserDTO> getUserTop() {
        List<RatingUserDTO> users = new ArrayList<>();
        userRepository.findAllByOrderByRatingDesc().forEach(user -> {
            var userToSend = new RatingUserDTO();
            userToSend.setUsername(user.getUsername());
            userToSend.setRating(user.getRating());
            userToSend.setPicture(user.getPicture());
            users.add(userToSend);
        });
        return users;
    }
}
