package com.example.hangman.game.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateGameDTO {
    private String word;
    private Date playDate;
    private Integer timePlayed;
    private Boolean win;
    private Long user_id;
}
