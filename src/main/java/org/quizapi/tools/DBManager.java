package org.quizapi.tools;

//"jdbc:sqlite:/home/wsl/IdeaProjects/GuessTheTitle/src/main/resources/db/" + fileName;

import java.io.File;
import java.sql.*;

public class DBManager {

    static String url = "jdbc:";

//    public DBManager(String path, String fileName){
//        this.url = "jdbc:sqlite:" + path + fileName;
//    }

    public static boolean doesThisFileExist(String filePath){
        File file = new File(filePath);
        return file.exists();
    }

    public static void createDatabase(String db, String filepath) {

        if (!doesThisFileExist(filepath)){
            //String url = "jdbc:sqlite:/home/wsl/IdeaProjects/GuessTheTitle/src/main/resources/db/" + fileName;
            url += (db + filepath);
            System.out.println(url);
            try (Connection conn = DriverManager.getConnection(url)) {
                if (conn != null) {
                    DatabaseMetaData meta = conn.getMetaData();
                    System.out.println("The driver name is " + meta.getDriverName());
                    System.out.println("A new database has been created.");
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }else{
            System.out.println("Database already exists.");
        }

    }

    public static Connection getConnection(){
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

    public static void createNewTable(String sql) {

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
            System.out.println("New table created");
        } catch (SQLException e) {
            System.out.println("[createNewTable] "+ e.getMessage());
        }
    }


//    public static void main(String[] args) {
//        createDatabase("test.db");
//    }
}