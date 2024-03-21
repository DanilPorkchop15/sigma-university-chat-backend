package com.example.hangman.user.model;
import com.example.hangman.game.model.Game;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", length = 20, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "date_registered")
    private Date dateRegistered = new Date();

    @Column(name = "rating")
    private Integer rating = 500;

    @Column(name = "picture ", columnDefinition = "LONGTEXT" )
    private String picture;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Game> game;

}
