package org.quizapi.controllers;

import org.quizapi.models.beans.Player;
import org.quizapi.models.daos.PlayerDAO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    //@Autowired
    //private PlayerRepository playerRepository;

    private PlayerDAO playerDAO;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED) //
    public String createPlayer(@RequestBody Player player) {
        Timestamp timestamp = null;
        Player savedPlayer = new Player(player.getName(), player.getScore(), timestamp = new Timestamp(System.currentTimeMillis()));
        playerDAO.insert(player);
        return savedPlayer.toJson();
    }

    @GetMapping
    public List<Player> listPlayers(){
        return playerDAO.toList();
    }

    @GetMapping("/{name}")
    public String searchPlayerByName(@PathVariable String name){
        Player player = playerDAO.selectByName(name);
        if (player.getName() == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND); //HTTP404
        return player.toJson();

    }

    @DeleteMapping("/{name}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT) //HTTP404
    public void deletePlayerByName(@PathVariable String name){
        String daoResponse = playerDAO.delete(name);
        if(daoResponse == "404") throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else if(daoResponse == "500") throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        else System.out.println("Requisição atendida");
    }


    @PutMapping("/{name}")
    public String updatePlayerByName(@RequestBody Player player){
        String daoResponse = playerDAO.update(player);
        if(daoResponse == "404") throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else if(daoResponse == "500") throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        else return daoResponse;
    }

}
