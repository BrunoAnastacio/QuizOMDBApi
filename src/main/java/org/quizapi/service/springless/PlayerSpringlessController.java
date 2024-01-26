package org.quizapi.service.springless;

import org.quizapi.util.exceptions.NotFoundIDException;
import org.quizapi.util.exceptions.ThisNameAlreadyExistsException;
import org.quizapi.domain.player.Player;
import org.quizapi.domain.player.PlayerDAO;

import java.sql.Timestamp;

public class PlayerSpringlessController {

    Player player;

    public PlayerSpringlessController(String name, int score) throws ThisNameAlreadyExistsException, NotFoundIDException {

        PlayerDAO playerDAO = new PlayerDAO();
        //Timestamp timestamp;
        player = new Player(name, score, new Timestamp(System.currentTimeMillis()));
        playerDAO.insert(player);
    }
    @Override
    public String toString() {
        return player.toString();
    }
}