package com.example.hangman.user.dto;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String username;
    private String password;
    private String firstName;
    private String secondName;
}
