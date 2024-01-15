package org.quizapi.models.daos;

import org.quizapi.models.beans.Player;
import org.quizapi.tools.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class PlayerDAO {
    Connection conn;

    PlayerDAO(){
        this.conn = DBManager.getConnection();
    }


    public void insert(Player player){

        //verificar se o nome/hash já não existe no BD
        //ver conversão de timestamp -> string -> timestamp
        //padronizar answers como score

//        INSERT INTO players (name, score, timestamp)
//        VALUES (?, ?, ?)
//        ON CONFLICT (name) DO UPDATE SET score = EXCLUDED.score, timestamp = EXCLUDED.timestamp;

//        Essa consulta verifica se o nome já existe na tabela.
//        Se houver um conflito, ela atualizará o escore e o carimbo de data/hora do registro existente.

        String sql = "INSERT INTO players (name, score, timestamp)"+"VALUES(?,?,?)";

        try{
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, player.getName());
            ps.setInt(2, player.getAnswers());
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
                String name = resultSet.getString(1);
                int score = resultSet.getInt(2);
                Player player = new Player(name, score);
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
            } finally{
                DBManager.closeConnection(conn);
            }
        }
    }

    public void delete(Player player){
        //String sql = "DELETE FROM player WHERE numero"
    }

    // //void insertPlayer (player, score) [CREATE]
    // //void updateScore (player, score) [UPDATE]
    // //Player selectByName (player) [READ]
    // //void delete (player) (DELETE)
    // //List<Player> listByScore (int sizeList) (READ)




}
