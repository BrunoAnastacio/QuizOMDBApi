package org.quizapi.controllers.springless;

import org.quizapi.models.beans.Player;

public class PlayerSpringlessController {

    Player player;

    public PlayerSpringlessController(String name, int answers ){
        player = new Player(name, answers);
    }

    @Override
    public String toString() {
        return player.toString();
    }
}