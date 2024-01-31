package org.quizapi.domain.player;

import org.quizapi.util.exceptions.NotFoundIDException;
import org.quizapi.util.exceptions.ThisNameAlreadyExistsException;
import org.quizapi.util.JPAUtil;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.List;

public class PlayerDAO {

    public EntityManager em;

    public PlayerDAO (){ //entity manager vem de fora
        em = JPAUtil.getEntityManager();
        System.out.println("PlayerDAO Construido. " + em.toString());
    }

    public String getEmStatus(){
        return em.toString();
    }

    public void insert(Player player) throws NotFoundIDException, ThisNameAlreadyExistsException {
        ifAintConnectedPleaseConnect();
        if(!thisNameExists(player.getName())) {
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

    public void update(Player newPlayerData) throws NotFoundIDException {
        //Long newId = (long)id;
        ifAintConnectedPleaseConnect();
        Player player = this.em.find(Player.class, newPlayerData.getId());
        try{
            this.em.getTransaction().begin();
            if(newPlayerData.getScore() > player.getScore()){
                this.em.merge(
                        new Player(
                                newPlayerData.getId(),
                                player.getName(),
                                newPlayerData.getScore(),
                                player.getTimestampSubscription(),
                                new Timestamp(System.currentTimeMillis())));
                this.em.getTransaction().commit();
                this.em.close();

            }else{
                this.em.close();

            }
        } catch(Exception e){
            try{
                if(em.isOpen()) {
                    this.em.getTransaction().rollback();
                    this.em.close();
                }
                System.out.println("Entity Manager fechado.");
                System.out.println(em.toString());
            } catch (Exception ex) {
                System.out.println("(Erro ao fechar o manager)" + ex.getMessage());
            } finally{
                System.out.println("[PlayerDAO: " + e.getMessage());
                throw new NotFoundIDException();
            }
        }
    }

    public void delete(Long id) throws NotFoundIDException {
        ifAintConnectedPleaseConnect();
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

    public Player searchById(Long id) throws Exception {
        ifAintConnectedPleaseConnect();
        try {
            this.em.getTransaction().begin();
            Player player = this.em.find(Player.class, id);
            this.em.close();
            if (player.isEmpty()) throw new NullPointerException();
            return player;
        } catch (NullPointerException n) {
            if (!em.isOpen()) this.em.close();
            System.out.println(n.getMessage());
            throw new NotFoundIDException();
        } catch (Exception e ){
            if (!em.isOpen()) this.em.close();
            System.out.println(e.getMessage());
            throw new Exception();
        }
    }

   public List <Player> toList() throws NotFoundIDException {
        String jpql = "SELECT p FROM Player p ORDER BY p.score DESC";
       ifAintConnectedPleaseConnect();
        try{
            return em.createQuery(jpql, Player.class)
                    .getResultList();
        } catch (Exception e) {
            if (!em.isOpen()) this.em.close();
            System.out.println(e.getMessage());
            throw new NotFoundIDException();
        }
   }

   private boolean thisNameExists(String name) throws NotFoundIDException {
       ifAintConnectedPleaseConnect();
        try{
            String jpql = "SELECT p FROM Player p WHERE p.name = :name";
            List <Player> list = em.createQuery(jpql, Player.class)
                    .setParameter("name", name)
                    .getResultList();
            return !list.isEmpty();
        } catch (Exception e) {
            if (!em.isOpen()) this.em.close();
            System.out.println(e.getMessage());
            throw new NotFoundIDException();
        }
   }

   private void ifAintConnectedPleaseConnect() {
       if (!em.isOpen()){
           em = JPAUtil.getEntityManager();
           System.out.println("PlayerDAO Reconstruido. " + em.toString());
       }

   }
}


