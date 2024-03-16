package com.example.hangman.user.service;

import com.example.hangman.user.dto.PictureDTO;
import com.example.hangman.user.model.User;

public interface ImageService {
    User updateProfilePicture (PictureDTO image, Long id);
    void deleteProfilePicture (Long id);
}
