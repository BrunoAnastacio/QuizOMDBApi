package org.quizapi.tools;

//"jdbc:sqlite:/home/wsl/IdeaProjects/GuessTheTitle/src/main/resources/db/" + fileName;

import java.sql.*;

public class DBManager {

    String url;

    public DBManager(String path, String fileName){
        this.url = "jdbc:sqlite:" + path + fileName;
    }

    public void createDatabase() {

        //String url = "jdbc:sqlite:/home/wsl/IdeaProjects/GuessTheTitle/src/main/resources/db/" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection(){
        Connection conn = null;
        try {
            // db parameters
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return conn;

    }

    public void createNewTable(String sql) {

        // SQL statement for creating a new table
//        String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
//                + "	id integer PRIMARY KEY,\n"
//                + "	name text NOT NULL,\n"
//                + "	capacity real\n"
//                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


//    public static void main(String[] args) {
//        createDatabase("test.db");
//    }
}