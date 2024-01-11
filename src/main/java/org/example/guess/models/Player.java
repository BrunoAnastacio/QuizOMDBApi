package org.example.guess.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Timestamp;

public class Player {
    private String name;
    //private int stars;
    private int answers;
    private Timestamp timestamp;

    public Player(){
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public Player(String name) {
        this.name = name;
        //this.stars = 5;
        this.answers = 0;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public Player(String name, int answers){
        this.name = name;
        this.answers = answers;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public String getName() {
        return name;
    }

//    //public int getStars() {
//        return stars;
//    }

//    //public void setStars(int stars) {
//        this.stars = stars;
//    }


    public int getAnswers() {
        return answers;
    }

    public void setAnswers() {
        this.answers++;
    }

    public void setAnswers(int answers){
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", answers=" + answers +
                ", timestamp=" + timestamp +
                '}';
    }

    public String toJson(){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }
}
