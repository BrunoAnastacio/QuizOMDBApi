package org.quizapi.domain.player;

import org.quizapi.dto.InsertDto;
import org.quizapi.dto.UpdateDto;
import org.quizapi.util.Validator;
import org.quizapi.util.exceptions.NotFoundIDException;
import org.quizapi.util.exceptions.ThisNameAlreadyExistsException;
import org.quizapi.util.persistence.JPAUtil;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.List;

public class PlayerDAO {

    public EntityManager em;

    public PlayerDAO (){
        em = JPAUtil.getEntityManager();
        System.out.println("PlayerDAO Construido. " + em.toString());
    }

    public String getEmStatus(){
        return em.toString();
    }

    public void insert(InsertDto player) throws NotFoundIDException, ThisNameAlreadyExistsException {
        ifAintConnectedPleaseConnect();
        try{
            if(Validator.validateThisName(player.name())){
                this.em.getTransaction().begin();
                this.em.persist(player);
                this.em.getTransaction().commit();
                this.em.close();
            }
        } catch(NotFoundIDException n){
            throw new ThisNameAlreadyExistsException("Nickname informado já existe");
        } catch (Exception e) {
            if (!em.isOpen()) {
                this.em.getTransaction().rollback();
                this.em.close();
            }
            System.out.println(e.getMessage());
            throw new NotFoundIDException("ID informado não existe");
        }
    }

    public void update(UpdateDto playerDto) throws NotFoundIDException {
        ifAintConnectedPleaseConnect();
        Player player = this.em.find(Player.class, playerDto.id());
        try{
            this.em.getTransaction().begin();
            if(Validator.validateFirstScore(playerDto.score(),player.getScore())){
                this.em.merge(
                        new Player(
                                playerDto.id(),
                                player.getName(),
                                playerDto.score(),
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

   public void ifAintConnectedPleaseConnect() {
       if (!em.isOpen()){
           em = JPAUtil.getEntityManager();
           System.out.println("PlayerDAO Reconstruido. " + em.toString());
       }

   }
}


