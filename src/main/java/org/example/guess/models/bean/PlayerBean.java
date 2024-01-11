package org.example.guess.models.bean;

import org.example.guess.connection.ConnectionFactory;
import org.example.guess.models.dao.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerBean {
    public PlayerBean(String name, int answer) throws SQLException {
        ConnectionFactory connection = new ConnectionFactory();
        Player player = new Player(name, answer);
        //connection.recoveryConnection().
        Connection conn = connection.recoveryConnection();
        String sql = "";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.executeUpdate();

        //https://unibb.alura.com.br/course/java-jdbc-banco-dados/task/124473
        //https://terminalroot.com.br/2022/06/como-conectar-ao-sqlite-com-java.html
    }


}
