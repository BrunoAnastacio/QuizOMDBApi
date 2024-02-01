package org.quizapi.util;

import org.quizapi.domain.player.Player;
import org.quizapi.domain.player.PlayerDAO;
import org.quizapi.util.exceptions.NotFoundIDException;

import java.util.List;

public class Validator {

    public static boolean validateFirstScore(int newScore, int score){
        return newScore > score;
    }

    public static boolean validateThisName(String name) throws NotFoundIDException {
        PlayerDAO p = new PlayerDAO();
        p.ifAintConnectedPleaseConnect();
        try{
            String jpql = "SELECT p FROM Player p WHERE p.name = :name";
            List<Player> list = p.em.createQuery(jpql, Player.class)
                    .setParameter("name", name)
                    .getResultList();
            return list.isEmpty();
        }catch (Exception e) {
            if (!p.em.isOpen()) p.em.close();
            System.out.println(e.getMessage());
            throw new NotFoundIDException();
        }
    }
}
