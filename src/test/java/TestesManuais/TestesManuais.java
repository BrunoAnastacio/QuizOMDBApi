package TestesManuais;

import org.quizapi.domain.title.OmdbGetTitle;
import org.quizapi.service.springless.PlayerSpringlessController;
import org.quizapi.util.exceptions.NotFoundIDException;
import org.quizapi.util.exceptions.ThisNameAlreadyExistsException;

public class TestesManuais {

    public static void main(String[] args) throws InterruptedException {
        //testSpringlessController();
        testGenerateURL();


    }

    static void testSpringlessController() throws ThisNameAlreadyExistsException, NotFoundIDException {
        String nome = "Eustaqio";
        int score = 3;
        PlayerSpringlessController player = new PlayerSpringlessController(nome, score);
    }

    static void testGenerateURL() throws InterruptedException {
        OmdbGetTitle conn = new OmdbGetTitle();
        String url = conn.generateURL("");
        url += "&type=movie";
        System.out.println(url);
    }





}
