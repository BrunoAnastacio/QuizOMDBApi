package org.example.guess.controllers.springless;

import org.example.guess.models.Player;

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
