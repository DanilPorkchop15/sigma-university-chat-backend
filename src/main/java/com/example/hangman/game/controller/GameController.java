package com.example.hangman.game.controller;
import com.example.hangman.game.dto.CreateGameDTO;
import com.example.hangman.game.service.impl.GameServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameServiceImpl gameService;

    public GameController(GameServiceImpl gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody CreateGameDTO request) {
        try {
            return new ResponseEntity<>(gameService.createGame(request), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("null", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> allGames(){
        var games = gameService.getAllGames();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> latestGames(@PathVariable Long id){
        var games = gameService.getLatestGames(id);
        if (games != null){
            return new ResponseEntity<>(games, HttpStatus.OK);
        }
        return new ResponseEntity<>("There is no games", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/user/{id}/wins")
    public ResponseEntity<?> wins(@PathVariable Long id){
        var games = gameService.getWins(id);
        if (games != null){
            return new ResponseEntity<>(games, HttpStatus.OK);
        }
        return new ResponseEntity<>("There is no wins", HttpStatus.NOT_FOUND);
    }
    @GetMapping("/user/{id}/loses")
    public ResponseEntity<?> loses(@PathVariable Long id){
        var games = gameService.getLoses(id);
        if (games != null){
            return new ResponseEntity<>(games, HttpStatus.OK);
        }
        return new ResponseEntity<>("There is no wins", HttpStatus.NOT_FOUND);
    }
}
