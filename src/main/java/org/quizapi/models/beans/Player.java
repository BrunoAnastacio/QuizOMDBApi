
package org.quizapi.models.beans;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Timestamp;

public class Player {
    private String name;
    private int score;
    private final Timestamp timestampSubscription;
    private final Timestamp timestampLastUpdate;
    private String id;

    public Player(){
        this.timestampSubscription = new Timestamp(System.currentTimeMillis());
        this.timestampLastUpdate = new Timestamp(System.currentTimeMillis());
    }

    //persistencia no BD
    public Player(String name, int score, Timestamp timestampSubscription){
        this.name = name;
        this.score = score;
        this.timestampLastUpdate = new Timestamp(System.currentTimeMillis());
        this.timestampSubscription = timestampSubscription;
        this.id = String.valueOf(name.hashCode());
    }

    //busca no BD
    public Player(String id, String name, int score, Timestamp timestampLastUpdate, Timestamp timestampSubscription) {
        this.name = name;
        this.score = score;
        this.timestampLastUpdate = timestampLastUpdate;
        this.timestampSubscription = timestampSubscription;
        this.id = id;
    }

    //update no BD
    public Player(String id, String name, int score, Timestamp timestampLastUpdate){
        this.name = name;
        this.score = score;
        this.timestampLastUpdate = timestampLastUpdate;
        this.timestampSubscription = null; //buscar no BD
        this.id = id;
    }

    //delete no BD
    public Player(String name){
        this.name = name;
//        this.score = score;
       this.timestampLastUpdate = null; //buscar no bd
        this.timestampSubscription = null; //buscar no bd
//        this.id = id;
    }

    public String getId(){
        return id;
    }

    public Timestamp getTimestampSubscription() {
        return timestampSubscription;
    }

    public Timestamp getTimestampLastUpdate() {
        return timestampLastUpdate;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", timestampSubscription=" + timestampSubscription +
                ", timestampLastUpdate=" + timestampLastUpdate +
                ", id='" + id + '\'' +
                '}';
    }

    public String toJson(){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
        //System.out.println(name.hashCode());
    }



}