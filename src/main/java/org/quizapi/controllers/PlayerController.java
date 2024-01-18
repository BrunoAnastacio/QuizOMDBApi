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

    private PlayerDAO playerDAO = new PlayerDAO();

    @PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED) //
    public String createPlayer(@RequestBody Player player) {
        playerDAO.insert(player);
        return player.toJson();
    }

    @GetMapping("/all")
    public List<String> listPlayers(){
        return playerDAO.toList();
    }

    @GetMapping("/search")
    public String searchPlayerById(@RequestParam("id") int id){
        Player player = playerDAO.selectById(id);
        //if (player.getName() == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND); //HTTP404
        return player.toJson();
    }

    @DeleteMapping("/delete/{name}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT) //HTTP404
    public void deletePlayerByName(@PathVariable String name){
        String daoResponse = playerDAO.delete(name);
        if(daoResponse == "404") throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else if(daoResponse == "500") throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        else System.out.println("Requisição atendida");
    }


    @PutMapping("/update/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String updatePlayerById(@PathVariable int id, @RequestBody Player player){
        try{
            return playerDAO.update(id, player);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
