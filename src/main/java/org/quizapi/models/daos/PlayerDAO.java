package org.quizapi.models.daos;

import org.jetbrains.annotations.NotNull;
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


    public void insert(@NotNull Player player) {

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

    public List<String> toList() {
        ResultSet resultSet;
        List<String> players = new ArrayList<>();
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
                players.add(player.toJson());
            }

            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            DBManager.closeConnection(conn);
        }
        return players;
    }

   public String update(int id, Player newPlayerData) {
        //revisar condições
       try{
           Player gotPlayer = selectById(id);
           gotPlayer.setScore(newPlayerData.getScore());
           gotPlayer.setTimestampLastUpdate(new Timestamp(System.currentTimeMillis()));
           this.conn = DBManager.getConnection();
           insert(gotPlayer);
           return gotPlayer.toJson();
       }catch (Exception e){
           System.out.println(e.getMessage());
           return "404";
       }
   }

    public Player selectById(int id) {
        // criar exceção para casos onde a consulta retorna vazio
        // criar exceção para casos onde o usuario passa dados errados
        String sql = "SELECT * FROM PLAYERS WHERE ID = ?";
        PreparedStatement ps;
        Player player;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            //ps.setString(1, n);
            ResultSet resultSet = ps.executeQuery();

            String id_response = resultSet.getString(1);
            String name = resultSet.getString(2);
            int score = resultSet.getInt(3);
            Timestamp timestampSubscription = resultSet.getTimestamp(4);
            Timestamp timestampLastUpdate = resultSet.getTimestamp(5);

            player = new Player(id_response, name, score, timestampSubscription, timestampLastUpdate);

            resultSet.close();
            ps.close();
            DBManager.closeConnection(conn);
            return player;

        } catch (SQLException e) {
            System.out.println("[selectByName]" + e);
        } finally {
            DBManager.closeConnection(conn);
        }
        return null;
    }

    public String delete(String n) {
        Player player = selectById(Integer.parseInt(n));
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
                System.out.println(e.getMessage());
                DBManager.closeConnection(conn);
                return "500";
            }
        }
    }
}




