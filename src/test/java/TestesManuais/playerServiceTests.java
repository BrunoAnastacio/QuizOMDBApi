package TestesManuais;

import org.quizapi.models.beans.Player;
import org.quizapi.services.PlayerService;
import java.util.List;

public class playerServiceTests {

    public static void main(String[] args) {
        PlayerService ps = new PlayerService();
        Player p = new Player("Oliveira", 66);
        //test_delete(ps, 555);
        //test_insert(ps, p);
        //test_searchById(ps,200);
        //test_toList(ps);
        //test_update(ps,2,31);
    }

    public static void test_delete(PlayerService ps, int id){
        ps.deleteById(id);
        //test id existe ok
        //test id não existe ok
    }

    public static void test_insert(PlayerService ps, Player p ){
        ps.insert(p);
        //nome que não existe ok
        //trava para nome que ja existe ok
    }

    public static void test_searchById(PlayerService ps, int id) {
        System.out.println(ps.getById(id).toJson());
        //test id existe ok
        //test id não existe ok
    }

    public static void test_toList(PlayerService ps){
        List<Player> all = ps.toList();
        System.out.println(all.stream().toList());

        //test ok
    }

    public static void test_update(PlayerService ps, int id, int score){
        //Player p = new Player( 3, 54)
        ps.updateById(id, score);
        // test atualização maior ok
        //test atualização menor ok
        //test atualização igual ok
        //test atualização item que não existe ok
    }



}
