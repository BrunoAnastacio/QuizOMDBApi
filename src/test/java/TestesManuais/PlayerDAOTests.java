package TestesManuais;


public class PlayerDAOTests {

    public static void main(String[] args) {
        //testDBManagerGetAndCloseConnection();
        //testeCreatePlayersDatabase();
        //testeInsertPlayer();
        //showTimestamp();
        //testeSelectByName();
        //testeToList();
        //testeDelete();
        //testeUpdate();
    }

//    static void testeCreatePlayersDatabase(){
//        try{
//            DBManager.createPlayersDatabase(AvaliableDBs.SQLITE.toString(), App.dbPlayersFilepath );
//            testDBManagerGetAndCloseConnection();
//        }catch (Exception e){
//            System.out.println(e);
//        }
//    }
//
//    static void testDBManagerGetAndCloseConnection(){
//        Connection conn = DBManager.getConnection();
//        if(conn == null){
//            System.out.println("Teste mal sucedido");
//        } else{
//            System.out.println("Teste bem sucedido");
//        }
//        System.out.println(conn);
//        DBManager.closeConnection(conn);
//
//    }
//
//    static void testeInsertPlayer(){
//        Timestamp t = new Timestamp(System.currentTimeMillis());
//        Player p = new Player("Benjamin Arrola",0, t);
//        PlayerDAO dao = new PlayerDAO();
//        dao.insert(p);
//        System.out.println("Inserido");
//    }

//    static void testeSelectByName(){
//        PlayerDAO dao = new PlayerDAO();
//        Player p = new Player();
//        p = dao.selectByName("JOSÉ");
//        System.out.println("Retorno:");
//        System.out.println(p.toJson());
//        System.out.println(p.getScore());
//
//    }

//    static void testeToList(){
//        PlayerDAO dao = new PlayerDAO();
//        Player p = new Player();
//        List<String> players = dao.toList();
//        System.out.println(players);
////        for(Player player: players){
////            System.out.println(player.toJson());
////        }
//
//    }
//
//    static void testeDelete(){
//        PlayerDAO dao = new PlayerDAO();
//        //Player p = new Player();
//        System.out.println(dao.delete("Geringonça"));
//    }
//
//    static void testeUpdate(){
//        Timestamp t = new Timestamp(System.currentTimeMillis());
//        Player p = new Player("Paul Molly",-6, t);
//        PlayerDAO dao = new PlayerDAO();
//        dao.update(p);
//    }

}
