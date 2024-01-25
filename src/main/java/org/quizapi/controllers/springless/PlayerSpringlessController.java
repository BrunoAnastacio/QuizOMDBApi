package org.quizapi.controllers.springless;

import org.quizapi.models.beans.Player;
import org.quizapi.models.daos.PlayerDAOJDBC;

import java.sql.Timestamp;

public class PlayerSpringlessController {

    Player player;

    public PlayerSpringlessController(String name, int score){
        PlayerDAOJDBC playerDAO = new PlayerDAOJDBC();
        //Timestamp timestamp;
        player = new Player(name, score, new Timestamp(System.currentTimeMillis()));
        playerDAO.insert(player);
    }
    @Override
    public String toString() {
        return player.toString();
    }
}