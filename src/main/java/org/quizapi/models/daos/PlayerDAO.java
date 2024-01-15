package org.quizapi.models.daos;

import org.quizapi.models.beans.Player;
import org.quizapi.tools.DBManager;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PlayerDAO {
    Connection conn;

    PlayerDAO(){
        this.conn = DBManager.getConnection();
    }


    public void insert(Player player){

        String sql = "INSERT INTO players " +
                "(ID, NAME, SCORE, TIMESTAMP_SUBSCRIPTION, TIMESTAMP_LAST_UPDATED)"
                +"VALUES(?,?,?,?,?)";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, player.getId());
            ps.setString(2, player.getName());
            ps.setInt(3, player.getScore());
            ps.setTimestamp(4, player.getTimestampSubscription());
            ps.setTimestamp(5, player.getTimestampLastUpdate());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            DBManager.closeConnection(conn);
        }
    }

    public Set<Player> toList() {
        ResultSet resultSet;
        Set<Player> players = new HashSet<>();
        String sql = "SELECT * FROM player ORDERBY score DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                //validar ordem da busca abaixo
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                int score = resultSet.getInt(3);
                Timestamp timestampSubscription = resultSet.getTimestamp(4);
                Timestamp timestampLastUpdate = resultSet.getTimestamp(5);

                Player player = new Player(id, name, score, timestampSubscription, timestampLastUpdate);
            }

            resultSet.close();
            ps.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        } finally{
            DBManager.closeConnection(conn);
        }
        return players;
    }

    public void update(Player player, int score){
        String sql = "UPDATE PLAYERS SET score = ? WHERE name = ?";
        PreparedStatement ps;

        try{
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setInt(1, player.getScore());
            ps.execute();
            ps.close();
            conn.close();
            conn.commit();
        }catch(SQLException e){
            try{
                conn.rollback();
            }catch(SQLException ex){
                throw new RuntimeException(ex);
            } finally{
                DBManager.closeConnection(conn);
            }
        }
    }

    public Player selectByName(String n){

        String sql = "SELECT * FROM PLAYERS WHERE NAME = ?";
        PreparedStatement ps;
        Player player = null;

        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, n);
            ResultSet resultSet = ps.executeQuery();

            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            int score = resultSet.getInt(3);
            Timestamp timestampSubscription = resultSet.getTimestamp(4);
            Timestamp timestampLastUpdate = resultSet.getTimestamp(5);

            player = new Player(id, name, score, timestampSubscription, timestampLastUpdate);

            resultSet.close();
            ps.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        } finally{
            DBManager.closeConnection(conn);
        }
        return player;

    }

    public String delete(String n){

        Player player = selectByName(n);
        String sql = "DELETE FROM player WHERE nome = ?";
        PreparedStatement ps;

        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1, n);
            ResultSet resultSet = ps.executeQuery();
            resultSet.close();
            ps.close();
            System.out.println("Jogador apagado com sucesso" + player.getName());
        } catch(Exception e){
            throw new RuntimeException(e);
        } finally{
            DBManager.closeConnection(conn);
        }

        //avaliar melhor saida
        System.out.println("Jogador apagado com sucesso" + player.toJson());
        return player.toJson();

    }

    // //void insertPlayer (player, score) [CREATE]
    // //void updateScore (player, score) [UPDATE]
    // //Player selectByName (player) [READ]
    // //void delete (player) (DELETE)
    // //List<Player> listByScore (int sizeList) (READ)





}
