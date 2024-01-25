package TestesManuais;

import org.quizapi.connections.OmdbConnection;
import org.quizapi.controllers.springless.PlayerSpringlessController;

public class TestesManuais {

    public static void main(String[] args) throws InterruptedException {
        //testSpringlessController();
        testGenerateURL();


    }

    static void testSpringlessController(){
        String nome = "Eustaqio";
        int score = 3;
        PlayerSpringlessController player = new PlayerSpringlessController(nome, score);
    }

    static void testGenerateURL() throws InterruptedException {
        OmdbConnection conn = new OmdbConnection();
        String url = conn.generateURL("");
        url += "&type=movie";
        System.out.println(url);
    }





}
