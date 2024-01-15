package org.quizapi.controllers.springless;

import org.quizapi.models.beans.Player;
import org.quizapi.models.daos.PlayerDAO;

public class PlayerSpringlessController {

    Player player;
    PlayerDAO playerDAO;

    public PlayerSpringlessController(String name, int answers ){
        player = new Player(name, answers);
        playerDAO.insert(player);

    }

    @Override
    public String toString() {
        return player.toString();
    }
}