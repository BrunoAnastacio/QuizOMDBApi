<<<<<<< HEAD
package org.example.guess;

import org.example.guess.models.dao.Player;
import org.example.guess.models.dao.Question;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

    Question q = new Question();
    Player p = new Player("Brunin");
    System.out.println(q.toJson());





    }

}


=======
package org.example.guess;

import org.example.guess.models.beans.Player;
import org.example.guess.models.beans.Question;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

    Question q = new Question();
    Player p = new Player("Brunin");
    System.out.println(q.toJson());





    }

}


>>>>>>> 0dfe66d (Organizando Beans e DAOs)
