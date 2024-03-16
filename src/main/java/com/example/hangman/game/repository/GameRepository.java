package com.example.hangman.game.repository;
import com.example.hangman.game.model.Game;
import com.example.hangman.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findAllBy ();
    List<Game> findAllByUserOrderByPlayDateDesc (User user);
    List<Game> findAllByUserAndWinIsTrue (User user);
    List<Game> findAllByUserAndWinIsFalse (User user);
}
