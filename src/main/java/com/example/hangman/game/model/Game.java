package com.example.hangman.game.model;
import com.example.hangman.user.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, name = "word", nullable = false)
    private String word;

    @Column(name = "play_date")
    private Date playDate;

    @Column(name = "time_played")
    private Integer timePlayed;

    @Column(name = "win", nullable = false)
    private Boolean win;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}