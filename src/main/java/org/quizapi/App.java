package org.quizapi;

import static org.quizapi.tools.AvaliableDBs.SQLITE;
import static org.quizapi.tools.DBManager.*;

public class App {

    public static String dbPlayersFilepath = "/home/wsl/IdeaProjects/GuessTheTitle/src/main/resources/db/players.db";
    public static String AppDbURL = SQLITE+ dbPlayersFilepath;

    public static void main(String[] args) {

        String dbPlayersFilepath = "/home/wsl/IdeaProjects/GuessTheTitle/src/main/resources/db/players.db";

        try {
            createPlayersDatabase(SQLITE.toString(), dbPlayersFilepath);
            //SpringApplication.run(QuizapiApplication.class, args);
        } catch (Exception e) {
            System.out.println("[main] " + e.getMessage());
        }

    }
}


