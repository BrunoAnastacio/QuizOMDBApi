package org.quizapi.service;

import org.quizapi.dto.DeleteDto;
import org.quizapi.dto.GetByIdDto;
import org.quizapi.dto.InsertDto;
import org.quizapi.dto.UpdateDto;
import org.quizapi.util.exceptions.NotFoundIDException;
import org.quizapi.util.exceptions.ThisNameAlreadyExistsException;
import org.quizapi.domain.player.Player;
import org.quizapi.domain.player.PlayerDAO;
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
    public void insert(@RequestBody InsertDto player) {
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
    public Player getById(GetByIdDto player){
        try{
            return playerDAO.searchById((long)player.id());
        } catch(NotFoundIDException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(DeleteDto player){
        try{
            playerDAO.delete((long)player.id());
        } catch(NotFoundIDException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public void updateById(@RequestBody UpdateDto player){
        if(player.id() == 0L || player.score() == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        try{
            playerDAO.update(player);
        } catch(NotFoundIDException n){
            System.out.println("Erro nfie" + n.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch(Exception e){
            System.out.println("Erro ise" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
