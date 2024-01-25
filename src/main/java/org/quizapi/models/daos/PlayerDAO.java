package org.quizapi.models.daos;

import org.quizapi.exceptions.NotFoundIDException;
import org.quizapi.exceptions.ThisNameAlreadyExistsException;
import org.quizapi.models.beans.Player;
import org.quizapi.util.JPAUtil;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.List;

public class PlayerDAO {

    private final EntityManager em;

    public PlayerDAO (){ //entity manager vem de fora
        em = JPAUtil.getEntityManager();
        System.out.println("Construindo PlayerDAO");
        System.out.println(em.toString());
        System.out.println("PlayerDAO Construido");
    }

    public String getEmStatus(){
        return em.toString();
    }

    public void insert(Player player) throws NotFoundIDException, ThisNameAlreadyExistsException {
        if(thisNameExists(player.getName())) {
            try {
                this.em.getTransaction().begin();
                this.em.persist(player);
                this.em.getTransaction().commit();
                this.em.close();
            } catch (Exception e) {
                if (!em.isOpen()) {
                    this.em.getTransaction().rollback();
                    this.em.close();
                }
                System.out.println(e.getMessage());
                throw new NotFoundIDException();
            }
        } else{
            throw new ThisNameAlreadyExistsException();
        }
    }

    public void update(int id, int score) throws NotFoundIDException {
        Long newId = (long)id;
        Player player = this.em.find(Player.class, newId);
        try{
            this.em.getTransaction().begin();
            if(score > player.getScore()){
                this.em.merge(
                        new Player(
                                newId,
                                player.getName(),
                                score,
                                player.getTimestampSubscription(),
                                new Timestamp(System.currentTimeMillis())));
                this.em.getTransaction().commit();
                this.em.close();
            }else{
                this.em.close();
            }
        } catch(Exception e){
            if(!em.isOpen()){
                this.em.getTransaction().rollback();
                this.em.close();
            }
            System.out.println(e.getMessage());
            throw new NotFoundIDException();
        }
    }

    public void delete(Long id) throws NotFoundIDException {
        try {
            this.em.getTransaction().begin();
            Player player = this.em.find(Player.class, id);
            this.em.remove(player);
            this.em.getTransaction().commit();
            this.em.close();
        }catch(Exception e){
            if(!em.isOpen()){
                this.em.getTransaction().rollback();
                this.em.close();
            }
            System.out.println(e.getMessage());
            throw new NotFoundIDException();
        }
    }

    public Player searchById(Long id) throws NotFoundIDException {
        try {
            this.em.getTransaction().begin();
            Player player = this.em.find(Player.class, id);
            this.em.close();
            return player;
        } catch (Exception e) {
            if (!em.isOpen()) this.em.close();
            System.out.println(e.getMessage());
            throw new NotFoundIDException();
        }
    }

//    public List<Player> toList(){
//        String jpql = "SELECT p FROM Player p";
//        return em.createQuery("FROM " + Player.class.getName()).getResultList();
//                //em.createQuery(jpql, Player.class).getResultList();
//
//            //return entityManager.createQuery("FROM " +
//                    //Cliente.class.getName()).getResultList();
//    }

    public List<Player> searchByName(String name){
        String jpql = "SELECT p FROM Player p WHERE p.name = :name";
        return em.createQuery(jpql, Player.class)
                .setParameter("name", name)
                .getResultList();
    }

   @SuppressWarnings("unchecked")
   public List <Player> toList() throws NotFoundIDException {
        try{
            return em.createQuery("FROM " + Player.class.getName())
                    .getResultList();
        } catch (Exception e) {
            if (!em.isOpen()) this.em.close();
            System.out.println(e.getMessage());
            throw new NotFoundIDException();
        }
   }

   private boolean thisNameExists(String name) throws NotFoundIDException {
        try{
            String jpql = "SELECT p FROM Player p WHERE p.name = :name";
            List <Player> l = em.createQuery(jpql, Player.class)
                    .setParameter("name", name)
                    .getResultList();
            return !l.isEmpty();
        } catch (Exception e) {
            if (!em.isOpen()) this.em.close();
            System.out.println(e.getMessage());
            throw new NotFoundIDException();
        }
   }
}


