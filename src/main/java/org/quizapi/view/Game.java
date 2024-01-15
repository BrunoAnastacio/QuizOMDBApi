package org.quizapi.view;

import org.quizapi.controllers.springless.PlayerSpringlessController;
import org.quizapi.models.beans.Question;
import org.quizapi.tools.GameManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Game {

    public static void main(String[] args) throws IOException, InterruptedException {

        String name = "Brunin Jogador Caro";
        int stars = 5, answers = 0;

        do {
            int r;
            List<Object> opcoes = new ArrayList<>();
            Question q = new Question();
            //System.out.println("qcd");
            System.out.println(q.toJson());
            System.out.println(GameManager.getStatus());
            System.out.println(GameManager.getCountRequests() - 5 + " tentativas excedentes de requisições realizadas.");
            System.out.println("Sinopse: " + q.getPlot());

            for (int i = 0; i < 5; i++) {
                System.out.println(i + 1 + " - " + q.getOptions(i));
            }

            System.out.println("========================================");
            System.out.println("\n Qual é o nome do filme?");

            Scanner scanner = new Scanner(System.in);
            r = scanner.nextInt();

            if (q.getOptions(r - 1).equals(q.getRightTitle())) {
                System.out.println("Resposta Correta");
                answers++;
                //p1.setAnswers();
                System.out.println(q.getRightTitle());
                System.out.println(q.getPlot());

            } else {
                System.out.println("========================================");
                System.out.println("Resposta Errada");
                System.out.println("Resposta Certa é: " + q.getRightTitle());
                System.out.println(q.getPlot());
                //p1.subStars();
                stars--;
            }

            final String os = System.getProperty("os.name");
            //Runtime.getRuntime().exec("clear");
            clear();
            System.out.println("========================================");
            System.out.println("Estrelas restantes: " + stars);
            System.out.println("Respostas: " + answers);
            System.out.println("========================================");

            GameManager.resetCountRequests();

        } while (stars > 0);

        System.out.println("Respostas Corretas: " + answers);
        PlayerSpringlessController player = new PlayerSpringlessController(name, answers);
        player.toString();
        //chamar ranking
    }

    public static void clear() throws IOException, InterruptedException {
        //Limpa a tela no windows, no linux e no MacOS
        try{
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }catch(Exception exception){
            System.out.println("Impossível limpar tela");
        }

    }
}
