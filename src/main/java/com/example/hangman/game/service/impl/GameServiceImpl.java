package com.example.hangman.game.service.impl;

import com.example.hangman.game.dto.CreateGameDTO;
import com.example.hangman.game.dto.UsersGameDTO;
import com.example.hangman.game.model.Game;
import com.example.hangman.game.repository.GameRepository;
import com.example.hangman.game.service.GameService;
import com.example.hangman.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public GameServiceImpl(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Game createGame(CreateGameDTO request) throws Exception {
        var game = new Game();
        game.setWord(request.getWord());
        game.setPlayDate(request.getPlayDate());
        game.setTimePlayed(request.getTimePlayed());
        game.setWin(request.getWin());
        game.setUser(userRepository.findUserById(request.getUser_id()));
        return gameRepository.save(game);
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAllBy();
    }

    @Override
    public List<UsersGameDTO> getLatestGames(Long id) {
        var user = userRepository.findUserById(id);
        return createUsersGameDTO(gameRepository.findAllByUserOrderByPlayDateDesc(user));
    }

    @Override
    public List<UsersGameDTO> getWins(Long id) {
        var user = userRepository.findUserById(id);
        return createUsersGameDTO(gameRepository.findAllByUserAndWinIsTrue(user));
    }

    @Override
    public List<UsersGameDTO> getLoses(Long id) {
        var user = userRepository.findUserById(id);
        return createUsersGameDTO(gameRepository.findAllByUserAndWinIsFalse(user));
    }

    private List<UsersGameDTO> createUsersGameDTO (List<Game> oldGames){
        List<UsersGameDTO> games = new ArrayList<>();
        oldGames.forEach(game -> {
            var newGame = new UsersGameDTO();
            newGame.setId(game.getId());
            newGame.setWord(game.getWord());
            newGame.setPlayDate(game.getPlayDate());
            newGame.setTimePlayed(game.getTimePlayed());
            games.add(newGame);
        });
        return games;
    }

}
