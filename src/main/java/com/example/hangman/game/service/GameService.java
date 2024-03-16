package com.example.hangman.game.service;
import com.example.hangman.game.dto.CreateGameDTO;
import com.example.hangman.game.dto.UsersGameDTO;
import com.example.hangman.game.model.Game;

import java.util.List;

public interface GameService {
    Game createGame(CreateGameDTO request) throws Exception;
    List<Game> getAllGames ();
    List<UsersGameDTO> getLatestGames (Long id);
    List<UsersGameDTO> getWins (Long id);
    List<UsersGameDTO> getLoses (Long id);
}

