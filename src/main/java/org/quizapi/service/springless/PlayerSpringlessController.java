package org.quizapi.service.springless;

import org.quizapi.dto.InsertDto;
import org.quizapi.util.exceptions.NotFoundIDException;
import org.quizapi.util.exceptions.ThisNameAlreadyExistsException;
import org.quizapi.domain.player.Player;
import org.quizapi.domain.player.PlayerDAO;

public class PlayerSpringlessController {

    Player player;

    public PlayerSpringlessController(InsertDto player) throws ThisNameAlreadyExistsException, NotFoundIDException {

        PlayerDAO playerDAO = new PlayerDAO();
        playerDAO.insert(player);
    }
    @Override
    public String toString() {
        return player.toString();
    }
}