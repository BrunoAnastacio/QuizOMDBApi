package TestesManuais;

import org.quizapi.controllers.springless.PlayerSpringlessController;
import org.quizapi.tools.DBManager;

import java.sql.Connection;

public class TestesManuais {

    public static void main(String[] args) {
        //testSpringlessController();

    }

    static void testSpringlessController(){
        String nome = "Eustaqio";
        int score = 3;
        PlayerSpringlessController player = new PlayerSpringlessController(nome, score);
    }





}
