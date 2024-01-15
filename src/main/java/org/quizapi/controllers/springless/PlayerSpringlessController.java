package org.quizapi.controllers.springless;

import org.quizapi.models.beans.Player;
import org.quizapi.models.daos.PlayerDAO;

import java.sql.Timestamp;

public class PlayerSpringlessController {

    Player player;
    PlayerDAO playerDAO;
    Timestamp timestamp = null;

    public PlayerSpringlessController(String name, int answers){
        player = new Player(name, answers, timestamp = new Timestamp(System.currentTimeMillis()));
        playerDAO.insert(player);

    }

    @Override
    public String toString() {
        return player.toString();
    }
}