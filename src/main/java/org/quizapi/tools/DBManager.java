package org.quizapi.tools;

import org.quizapi.App;

import java.io.File;
import java.sql.*;

public class DBManager {

    private static String initURLString(){
        return "jdbc:";
    }

    private static boolean doesThisFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    public static void createPlayersDatabase(String db, String filepath) {

        String sql = "CREATE TABLE IF NOT EXISTS " +
                "players (" +
                "ID integer PRIMARY KEY," +
                "NAME varchar (100) NOT NULL, " +
                "SCORE integer NOT NULL, " +
                "TIMESTAMP_SUBSCRIPTION varchar, " +
                "TIMESTAMP_LAST_UPDATED varchar)";

        //url += (db + filepath);
        String url = initURLString() + db + filepath;

        if (!doesThisFileExist(filepath)) {
            try (Connection conn = DriverManager.getConnection(url)) {
                try {
                    if (conn != null) {
                        DatabaseMetaData meta = conn.getMetaData();
                        System.out.println("The driver name is " + meta.getDriverName());
                        System.out.println("A new database has been created.");
                        Statement stmt = conn.createStatement();
                        stmt.execute(sql);
                        System.out.println("New table created");
                    }

                } catch (Exception e) {
                    System.out.println("[createPlayersDatabase I] " + e.getMessage());
                } finally {
                    closeConnection(conn);
                }
            } catch (Exception e) {
                System.out.println("[createPlayersDatabase II] " + e.getMessage());
            }
        } else {
            System.out.println("Database in "+ url +" already exists.");
        }
    }

    public static Connection getConnection() {
        //url += path;
        String url = initURLString() + App.AppDbURL;
        Connection conn = null;
        try {
            // db parameters
            System.out.println(url);
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

        } catch (Exception e) {
            System.out.println("[getConnection] " + e.getMessage());
        }
        return conn;
    }

    public static void closeConnection(Connection conn){
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Conexão fechada.");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
