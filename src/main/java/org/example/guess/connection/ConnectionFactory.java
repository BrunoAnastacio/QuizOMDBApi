<<<<<<< HEAD
package org.example.guess.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {

    public Connection recoveryConnection() {
        try {
            // Cria a conexão com o banco de dados
            Connection connection = DriverManager.getConnection("jdbc:sqlite:base.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

//            //comandos de configuração do BD
//            System.out.println("Conexão com DB aberta por 5 segundos");
//            System.out.println(statement);
//            Thread.sleep(5000);
//            System.out.println("Conexão com DB encerrada");

            return connection;

            //--------------------------------
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
//        finally{
//            try{
//                if (connection != null) connection.close();
//            } catch (SQLException exception){
//                System.err.println("Impossível encerrar conexão. " + exception.getMessage());
//            }
//      }
    }
}

=======
package org.example.guess.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {

    public Connection recoveryConnection() {
        try {
            // Cria a conexão com o banco de dados
            Connection connection = DriverManager.getConnection("jdbc:sqlite:base.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

//            //comandos de configuração do BD
//            System.out.println("Conexão com DB aberta por 5 segundos");
//            System.out.println(statement);
//            Thread.sleep(5000);
//            System.out.println("Conexão com DB encerrada");

            return connection;

            //--------------------------------
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
//        finally{
//            try{
//                if (connection != null) connection.close();
//            } catch (SQLException exception){
//                System.err.println("Impossível encerrar conexão. " + exception.getMessage());
//            }

        //insert
            //recoveryConnection()
            //statement.executeUpdate(sql)
            //closeConnection()
        //select
        //delete
        //create
//      }
    }
}

>>>>>>> 65bc516 (desenvolvendo PlayerBean)
