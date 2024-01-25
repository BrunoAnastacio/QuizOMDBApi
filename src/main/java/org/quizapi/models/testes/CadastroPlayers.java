package org.quizapi.models.testes;

import org.quizapi.models.beans.Player;

import javax.persistence.*;


public class CadastroPlayers {

    public static void main(String[] args) {

        Player p = new Player("Orquestrina", 42);
        System.out.println(p.toJson());
        //p.toJson();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("quizapi");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(p); //goes to PlayerDAO
        em.getTransaction().commit();
        em.close();


    }
}
