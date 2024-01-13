<<<<<<< HEAD
package org.example.guess.controllers.springless;

import org.example.guess.models.dao.Player;

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
=======
package org.example.guess.controllers.springless;

import org.example.guess.models.beans.Player;

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
>>>>>>> 0dfe66d (Organizando Beans e DAOs)
