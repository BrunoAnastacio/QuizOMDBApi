package org.quizapi.models.daos;

import org.quizapi.models.beans.Player;
import org.quizapi.tools.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PlayerDAO {
    Connection conn;

    public PlayerDAO() {
        this.conn = DBManager.getConnection();
    }


    public void insert(Player player) {

        String sql = "INSERT INTO players (ID, NAME, SCORE, TIMESTAMP_SUBSCRIPTION, TIMESTAMP_LAST_UPDATED) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON CONFLICT (ID) DO UPDATE " +
                "SET SCORE = EXCLUDED.SCORE, " +
                "TIMESTAMP_LAST_UPDATED = EXCLUDED.TIMESTAMP_LAST_UPDATED";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, player.getId());
            ps.setString(2, player.getName());
            ps.setInt(3, player.getScore());
            //ps.setTimestamp(4, player.getTimestampSubscription());
            //ps.setTimestamp(5, player.getTimestampLastUpdate());

            ps.setString(4, String.valueOf(player.getTimestampSubscription()));
            ps.setString(5, String.valueOf(player.getTimestampLastUpdate()));


            ps.execute();
            ps.close();

            System.out.println("Jogador persistido: " + player.toJson());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBManager.closeConnection(conn);
        }
    }

    public List<Player> toList() {
        ResultSet resultSet;
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM players ORDER BY score DESC";

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
                players.add(player);
            }

            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeException();

        } finally {
            DBManager.closeConnection(conn);
        }
        return players;
    }
   public String update(Player newPlayerData) {

            //revisar condições
       Player gotPlayer = selectByName(newPlayerData.getName());
       if(gotPlayer.getName() == null || newPlayerData.getScore() > gotPlayer.getScore()){
           this.conn = DBManager.getConnection();
           insert(newPlayerData);
           return newPlayerData.toJson();
       }
       else if (gotPlayer.getName() != null) return "404";
       else return "500";


   }

    //==================================================================
    //SENTENÇA A VALIDAR:
    //  if(gotPlayer.getName() == null ||
    //     newPlayerData.getScore() > gotPlayer.getScore())
    //      insert(newPlayerData);

    //TABELA VERDADE:
    //gotPlayer.getName() == null                         V  V  F  F
    //newPlayerData.getScore() > gotPlayer.getScore()     V  F  V  F
    //insert(newPlayerData);                              V  V  V  F

    //CENÁRIOS:
    //Salvar score 0 para um nome que não existe        V    V
    //Salvar score > para um nome que existe            V       V
    //Salvar score <= para nome que existe              F          F
    //==================================================================

    public Player selectByName(String n) {
        // criar exceção para casos onde a consulta retorna vazio
        // criar exceção para casos onde o usuario passa dados errados
        String sql = "SELECT * FROM PLAYERS WHERE NAME = ?";
        PreparedStatement ps;
        Player player = null;

        try {
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBManager.closeConnection(conn);
        }
        return player;

    }

    public String delete(String n) {

        Player player = selectByName(n);
        if (player.getName() == null) {
            return "404";
        } else {
            this.conn = DBManager.getConnection();
            String sql = "DELETE FROM players WHERE name = ?";
            PreparedStatement ps;

            try {
                ps = conn.prepareStatement(sql);
                ps.setString(1, n);
                ps.executeUpdate();
                ps.close();
                DBManager.closeConnection(conn);
                return("Jogador apagado com sucesso:" + player.getName());
            } catch (Exception e) {
                //throw new RuntimeException(e);
                e.printStackTrace();
                DBManager.closeConnection(conn);
                return "500";
            }


        }


    }
}

    // //void insertPlayer (player, score) [CREATE]
    // //void updateScore (player, score) [UPDATE]
    // //Player selectByName (player) [READ]
    // //void delete (player) (DELETE)
    // //List<Player> listByScore (int sizeList) (READ)






