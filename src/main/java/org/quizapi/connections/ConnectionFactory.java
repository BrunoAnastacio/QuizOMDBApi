//package org.quizapi.connections;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.quizapi.tools.DBManager;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//public class ConnectionFactory {
//
//    public Connection recoveryConnection() {
//        try{
//            return createDataSource().getConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private HikariDataSource createDataSource(){
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl("jdbc:sqlite://localhost:3306/QuizAPI");
//        config.setUsername("root");
//        config.setPassword("root");
//        config.setMaximumPoolSize(10);
//
//        return new HikariDataSource(config);
//    }
////    DBManager manager = new DBManager("foo","goo");
////    manager.createNewTable( "foo");
//
//}
//
