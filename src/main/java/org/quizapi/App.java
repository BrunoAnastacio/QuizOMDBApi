package org.quizapi;

import static org.quizapi.tools.AvaliableDBs.SQLITE;
import static org.quizapi.tools.DBManager.*;

//@SpringBootApplication
public class App {

    String db = SQLITE.toString();

    public static void main(String[] args) {

        String sql = "CREATE TABLE IF NOT EXISTS players (id integer PRIMARY KEY,score integer NOT NULL)";

        String dbPlayersFilepath = "/home/wsl/IdeaProjects/GuessTheTitle/src/main/resources/db/players.db";

        createDatabase(SQLITE.toString(), dbPlayersFilepath);
        createNewTable(sql);
        //SpringApplication.run(QuizapiApplication.class, args);

    }
    //criar database
    //questao
    //enviar questao





}


