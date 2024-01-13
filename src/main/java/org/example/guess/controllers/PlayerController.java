package org.example.guess.controllers;

import org.example.guess.models.dao.Player;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {
    //@Autowired -- descomentar
    //private PlayerRepository playerRepository; -- descomentar

    @PostMapping(consumes = "application/json", produces = "application/json")
    //-- descomentar public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
    public String createPlayer(@RequestBody Player player) {
        //-- descomentar Player savedPlayer = playerRepository.save(player);
        Player savedPlayer = new Player(player.getName(), player.getAnswers());
        //-- descomentar return ResponseEntity.ok(savedPlayer);

        return savedPlayer.toJson();

    }
}

