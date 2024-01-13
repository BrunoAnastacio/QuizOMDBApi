
package org.example.guess.models.dao;


import org.example.guess.connections.ConnectionFactory;
import org.example.guess.models.beans.Player;

import java.sql.Connection;
import java.sql.SQLException;

public class PlayerDAO {
    public PlayerDAO(String name, int answer) throws SQLException {
        Player player = new Player(name, answer);
        String sql = "INSERT INTO player VALUES(1, "+ player.getName()+","+ player.getAnswers()+")";
        //Statement statement = connection.prepareStatement(sql);
        Connection connection = new ConnectionFactory().recoveryConnection();

        //statement.executeUpdate(sql);

        //https://unibb.alura.com.br/course/java-jdbc-banco-dados/task/124473
        //https://terminalroot.com.br/2022/06/como-conectar-ao-sqlite-com-java.html
    }

    //insert
        //recoveryConnectionSQLite()
        //statement.executeUpdate(sql)
        //closeConnection()


}

