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
        if(player.getName() == null || player.getScore() == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        try{
            playerDAO.insert(player);
        } catch (ThisNameAlreadyExistsException t){
            System.out.println("[PlayerService.insert] :" + t.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //400
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
    public Player getById(int id){
        try{
            System.out.println(playerDAO.getEmStatus());
            return playerDAO.searchById((long)id);
        } catch(NotFoundIDException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(int id){
        try{
            playerDAO.delete((long)id);
        } catch(NotFoundIDException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public void updateById(@RequestBody Player player){
        if(player.getId() == 0 || player.getScore() == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        try{
            playerDAO.update(player);
        } catch(NotFoundIDException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
