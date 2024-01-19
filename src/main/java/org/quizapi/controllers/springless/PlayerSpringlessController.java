package org.quizapi.controllers.springless;

import org.quizapi.models.beans.Player;
import org.quizapi.models.daos.PlayerDAO;

import java.sql.Timestamp;

public class PlayerSpringlessController {

    Player player;

    public PlayerSpringlessController(String name, int score){
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