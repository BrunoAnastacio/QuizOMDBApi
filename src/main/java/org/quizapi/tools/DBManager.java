package org.quizapi.tools;

import java.io.File;
import java.sql.*;

public class DBManager {

    static String url = "jdbc:";

    private static boolean doesThisFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    public static void createPlayersDatabase(String db, String filepath) throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS players (ID integer PRIMARY KEY,SCORE integer NOT NULL, TIMESTAMP varchar)";
        url += (db + filepath);


        if (!doesThisFileExist(filepath)) {
            //System.out.println(url);
            Connection conn = DriverManager.getConnection(url);
            try{
                if (conn != null) {
                    DatabaseMetaData meta = conn.getMetaData();
                    System.out.println("The driver name is " + meta.getDriverName());
                    System.out.println("A new database has been created.");
                    Statement stmt = conn.createStatement();
                    stmt.execute(sql);
                    System.out.println("New table created");
                }

            } catch (Exception e) {
                System.out.println("[createPlayersDatabase] " + e.getMessage());
            } finally{
                closeConnection(conn);
            }
        } else {
            System.out.println("Database in "+ url +" already exists.");
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // db parameters
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
