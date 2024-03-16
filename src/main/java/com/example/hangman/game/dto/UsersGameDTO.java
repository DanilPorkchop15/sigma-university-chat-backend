package com.example.hangman.game.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UsersGameDTO {
    private Long id;
    private String word;
    private Date playDate;
    private Integer timePlayed;
    private Boolean win;
}
