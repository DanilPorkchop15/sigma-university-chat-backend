package com.example.hangman.user.controller;
import com.example.hangman.user.dto.*;
import com.example.hangman.user.model.User;
import com.example.hangman.user.service.impl.ImageServiceImpl;
import com.example.hangman.user.service.impl.LoginServiceImpl;
import com.example.hangman.user.service.impl.RatingServiceImpl;
import com.example.hangman.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserServiceImpl userService;
    private final LoginServiceImpl loginService;
    private final RatingServiceImpl ratingService;
    private final ImageServiceImpl imageService;

    @Autowired
    public UserController(
            UserServiceImpl userService,
            LoginServiceImpl loginService,
            RatingServiceImpl ratingService,
            ImageServiceImpl imageService) {
        this.userService = userService;
        this.loginService = loginService;
        this.ratingService = ratingService;
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> getUserById (@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO request) {
        try {
            var createdUser = userService.createUser(request);
            if (createdUser != null){
                return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
            }
            return new ResponseEntity<>("User already exist", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO request) {
        try {
            var loggedUser = loginService.login(request);
            if (loggedUser != null) {
                return new ResponseEntity<>(loggedUser, HttpStatus.OK);
            }
            return new ResponseEntity<>("User doesn't exist", HttpStatus.ALREADY_REPORTED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}/rating")
    public ResponseEntity<?> updateUserRating(
            @PathVariable("id") Long id,
            @RequestBody UpdateRatingDTO updatedUser
    ) {
        User user = ratingService.update(updatedUser, id);
        if (user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/top")
    public ResponseEntity<List<RatingUserDTO>> getUserTop(){
        List<RatingUserDTO> users = ratingService.getUserTop();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser (@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

    @PutMapping("{id}/picture")
    public ResponseEntity<?> putProfilePicture(
            @PathVariable Long id,
            @RequestBody PictureDTO image
    ){
        var user = imageService.updateProfilePicture(image, id);
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("{id}/picture")
    public ResponseEntity<?> deleteProfilePicture(
            @PathVariable Long id
    ) {
        imageService.deleteProfilePicture(id);
        return new ResponseEntity<>("Profile picture deleted", HttpStatus.OK);
    }


}
