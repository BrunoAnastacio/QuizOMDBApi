package org.quizapi.services;

import org.quizapi.exceptions.NotFoundIDException;
import org.quizapi.exceptions.ThisNameAlreadyExistsException;
import org.quizapi.models.beans.Player;
import org.quizapi.models.daos.PlayerDAO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/players/v2")
public class PlayerService {

    PlayerDAO playerDAO;

    public PlayerService(){
        playerDAO = new PlayerDAO();
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void insert(@RequestBody Player player) {
        try{
            playerDAO.insert(player);
        } catch (ThisNameAlreadyExistsException t){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY); //400
        } catch(Exception e){
            System.out.println("[PlayerService.insert] :" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/")
    public List<Player> toList(){
        try{
            List<Player> all = playerDAO.toList();
            all.forEach(Player::toJson);
            return all;
        } catch(NotFoundIDException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public Player getById(int id) throws NotFoundIDException {
        try{
            System.out.println(playerDAO.getEmStatus());
            return playerDAO.searchById((long)id);
        } catch(NotFoundIDException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/name")
//    public Player getByName(String name) {
//        return try {
//            playerDAO.searchByName(name);
//        } catch(NotFoundIDException n){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        } catch(Exception e){
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @DeleteMapping("/{id}")
    public void deleteById(int id){
        try{
            playerDAO.delete((long)id);
        } catch(NotFoundIDException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public void updateById(int id, int score){
        try{
            playerDAO.update(id, score);
        } catch(NotFoundIDException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
