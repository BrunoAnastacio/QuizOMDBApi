package org.quizapi.controllers;

import org.quizapi.models.beans.Player;
import org.quizapi.models.daos.PlayerDAO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("/players")
public class PlayerController {
    //@Autowired -- descomentar
    //private PlayerRepository playerRepository; -- descomentar

    PlayerDAO playerDAO;

    @PostMapping(consumes = "application/json", produces = "application/json")
    //-- descomentar public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
    public String createPlayer(@RequestBody Player player) {
        Timestamp timestamp = null;
        //-- descomentar Player savedPlayer = playerRepository.save(player);
        Player savedPlayer = new Player(player.getName(), player.getScore(), timestamp = new Timestamp(System.currentTimeMillis()));
        //-- descomentar return ResponseEntity.ok(savedPlayer);

        playerDAO.insert(player);

        return savedPlayer.toJson();

    }
}
