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


