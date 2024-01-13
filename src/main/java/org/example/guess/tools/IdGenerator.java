package org.example.guess.tools;

import java.util.Random;

public class IdGenerator {

    public static void main(String[] args) {
        Random random = new Random();
        int ID = random.nextInt(999999);
        String a = String.format("%06d",ID);
        System.out.println("tt0"+a);
    }
}
