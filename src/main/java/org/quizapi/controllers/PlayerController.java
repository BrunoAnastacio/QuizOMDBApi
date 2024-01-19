package org.quizapi.controllers;

import org.quizapi.exceptions.NotFoundIDException;
import org.quizapi.models.beans.Player;
import org.quizapi.models.daos.PlayerDAO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/players/v1")
public class PlayerController {
    private PlayerDAO playerDAO = new PlayerDAO();

    @PostMapping(value = "/n", consumes = "application/json", produces = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED) //
    public String createPlayer(@RequestBody Player player) {
        try {
            playerDAO.insert(player);
            return player.toJson();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/")
    public List<String> listPlayers() {
        return playerDAO.toList();
    }


    @GetMapping("")
    public String searchPlayerById(@RequestParam("id") int id) {
        try {
            Player player = playerDAO.selectById(id);
            return player.toJson();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}/del")
    @ResponseStatus(code = HttpStatus.NO_CONTENT) //HTTP204
    public void deletePlayerByName(@PathVariable int id) {
        Player player = playerDAO.selectById(id);
        //System.out.println(player.isEmpty());
        if (player.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        try {
            playerDAO.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Player updatePlayerById(@PathVariable int id, @RequestBody Player toUpdate) {
        try {
            System.out.println(toUpdate.toJson());
            return playerDAO.update(id, toUpdate);
        } catch (NotFoundIDException n) {
            System.out.println("Erro404");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); //erro404
        } catch (Exception e) {
            System.out.println("Erro500");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR); //erro500
        }
    }
}
