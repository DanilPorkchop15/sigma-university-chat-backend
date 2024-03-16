package com.example.hangman.user.service;

import com.example.hangman.user.dto.RatingUserDTO;
import com.example.hangman.user.dto.UpdateRatingDTO;
import com.example.hangman.user.model.User;

import java.util.List;

public interface RatingService {
    User update (UpdateRatingDTO user, Long id);
    List<RatingUserDTO> getUserTop ();
}
