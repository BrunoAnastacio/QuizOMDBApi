package org.quizapi.controllers.springless;

import org.quizapi.models.beans.Player;
import org.quizapi.models.daos.PlayerDAO;

import java.sql.Timestamp;

public class PlayerSpringlessController {

    Player player = null;

    public PlayerSpringlessController(String name, int score){
        Player player = null;
        PlayerDAO playerDAO = new PlayerDAO();
        Timestamp timestamp = null;
        player = new Player(name, score, timestamp = new Timestamp(System.currentTimeMillis()));
        playerDAO.insert(player);
    }

    @Override
    public String toString() {
        return player.toString();
    }
}