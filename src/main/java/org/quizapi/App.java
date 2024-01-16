package org.quizapi;

import org.quizapi.models.beans.Player;
import org.quizapi.tools.DBManager;

import java.sql.SQLException;

import static org.quizapi.tools.AvaliableDBs.SQLITE;
import static org.quizapi.tools.DBManager.*;

//@SpringBootApplication
public class App {

    String db = SQLITE.toString();
    public static String dbPlayersFilepath = "/home/wsl/IdeaProjects/GuessTheTitle/src/main/resources/db/players.db";
    public static String AppDbURL = SQLITE.toString() + dbPlayersFilepath;

    public static void main(String[] args) {

        String dbPlayersFilepath = "/home/wsl/IdeaProjects/GuessTheTitle/src/main/resources/db/players.db";

        try{
            createPlayersDatabase(SQLITE.toString(), dbPlayersFilepath);
            //SpringApplication.run(QuizapiApplication.class, args);
        }catch (Exception e ){
            System.out.println("[main] "+ e.getMessage());
        }

//        Player p = new Player("Bruno", 0);
//        Player q = new Player("Juciara", 8);
//        Player r = new Player("Bruno", 0);
//        System.out.println(p.getId());
//        System.out.println(q.getId());
//        System.out.println(r.getId());




    }
    //criar database
    //questao
    //enviar questao





}


