package org.quizapi.models.daos;

import org.jetbrains.annotations.NotNull;
import org.quizapi.exceptions.NotFoundIDException;
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
            System.out.println("[PlayerDAO insert()]: Jogador inserido com sucesso");

        } catch (SQLException e) {
            System.out.println("[PlayerDAO insert()]" + e.getMessage());
        } finally {
            DBManager.closeConnection(conn);
        }
    }

    public List<String> toList() throws NotFoundIDException {
        ResultSet resultSet;
        List<String> players = new ArrayList<>();
        String sql = "SELECT * FROM players ORDER BY score DESC";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
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

            if (players.isEmpty()) {
                System.out.println("[PlayerDAO toList()] NotFoundException");
                DBManager.closeConnection(conn);
                throw new NotFoundIDException();
            }
        } catch (SQLException e) {
            DBManager.closeConnection(conn);
            System.out.println("[PlayerDAO toList()] "+e.getMessage());
        }
        return players;
    }

   public Player update(int id, Player toUpdate) throws NotFoundIDException {
        Player gotPlayer = selectById(id);
        this.conn = DBManager.getConnection();
        if(gotPlayer.isEmpty())
        {
            System.out.println("[PlayerDAO update()] NotFoundException");
            DBManager.closeConnection(conn);
            throw new NotFoundIDException();
        }
        else{
            //criar um player pra inserir no banco
            Player updatedPlayer = new Player(
                    gotPlayer.getId(),
                    gotPlayer.getName(),
                    toUpdate.getScore(),
                    gotPlayer.getTimestampSubscription(),
                    new Timestamp(System.currentTimeMillis())
                );
            System.out.println(updatedPlayer);
            insert(updatedPlayer);
            DBManager.closeConnection(conn);
            return updatedPlayer;
        }
   }

    public Player selectById(int id) {
        String sql = "SELECT * FROM PLAYERS WHERE ID = ?";
        PreparedStatement ps;
        Player player;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
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
            System.out.println("[PlayerDAO selectById()]" + e);
            DBManager.closeConnection(conn);
            return null;
        }
    }

    public void delete(int id) {

        try{
            //Player gotPlayer = selectById(id);
            this.conn = DBManager.getConnection();
            String sql = "DELETE FROM players WHERE id = ?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            DBManager.closeConnection(conn);
        } catch(Exception e){
            DBManager.closeConnection(conn);
            System.out.println("[PlayerDAO delete()]" + e.getMessage());
        }
    }
}




