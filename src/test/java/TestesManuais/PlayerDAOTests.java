package TestesManuais;

import org.quizapi.App;
import org.quizapi.models.beans.Player;
import org.quizapi.models.daos.PlayerDAO;
import org.quizapi.tools.AvaliableDBs;
import org.quizapi.tools.DBManager;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Set;

public class PlayerDAOTests {

    public static void main(String[] args) {
        //testDBManagerGetAndCloseConnection();
        //testeCreatePlayersDatabase();
        //testeInsertPlayer();
        //showTimestamp();
        //testeSelectByName();
        //testeToList();
        testeDelete();
    }

    static void testeCreatePlayersDatabase(){
        try{
            DBManager.createPlayersDatabase(AvaliableDBs.SQLITE.toString(), App.dbPlayersFilepath );
            testDBManagerGetAndCloseConnection();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    static void testDBManagerGetAndCloseConnection(){
        Connection conn = DBManager.getConnection();
        if(conn == null){
            System.out.println("Teste mal sucedido");
        } else{
            System.out.println("Teste bem sucedido");
        }
        System.out.println(conn);
        DBManager.closeConnection(conn);

    }

    static void testeInsertPlayer(){
        Timestamp t = new Timestamp(System.currentTimeMillis());
        Player p = new Player("Benjamin Arrola",48, t);
        PlayerDAO dao = new PlayerDAO();
        dao.insert(p);
        System.out.println("Inserido");
    }

    static void testeSelectByName(){
        PlayerDAO dao = new PlayerDAO();
        Player p = new Player();
        p = dao.selectByName("Fabão");
        System.out.println("Retorno:");
        System.out.println(p.toJson());

    }

    static void testeToList(){
        PlayerDAO dao = new PlayerDAO();
        Player p = new Player();
        Set<Player> players = dao.toList();
        for(Player player: players){
            System.out.println(player.toJson());
        }

    }

    static void testeDelete(){
        PlayerDAO dao = new PlayerDAO();
        //Player p = new Player();
        System.out.println(dao.delete("geringonca"));
    }




}
