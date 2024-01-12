package org.example.guess.models.daos;

import org.example.guess.models.beans.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class PlayerDAO {
    Connection conn;
    PlayerDAO(Connection connection){
        this.conn = connection;
    }

    public void insert(Player player){
        //Player player = new Player();
        String sql = "INSERT INTO player (name, score, timestamp)"+"VALUES(?,?,?)";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, player.getName());
            ps.setInt(2, player.getAnswers());
            ps.execute();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
                String name = resultSet.getString(1);
                int score = resultSet.getInt(2);
                Player player = new Player(name, score);
            }

            resultSet.close();
            ps.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return players;

    }

    public void update(Player player){
        String sql = "UPDATE PLAYER SET score - ?";
        PreparedStatement ps;

        try{
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setInt(1, player.getAnswers());
            ps.execute();
            ps.close();
            conn.close();
            conn.commit();
        }catch(SQLException e){
            try{
                conn.rollback();
            }catch(SQLException ex){
                throw new RuntimeException(ex);
            }
        }
    }

    public void delete(Player player){
        //String sql = "DELETE FROM player WHERE numero"
    }


}
