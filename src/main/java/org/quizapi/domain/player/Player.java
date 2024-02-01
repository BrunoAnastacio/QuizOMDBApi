
package org.quizapi.domain.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "players")
public class Player {

    @Column(name = "TIMESTAMP_SUBSCRIPTION")
    private final Timestamp timestampSubscription;

    @Column(name = "TIMESTAMP_LAST_UPDATED")
    private final Timestamp timestampLastUpdate;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int score;

    public Player(){
        this.timestampSubscription = new Timestamp(System.currentTimeMillis());
        this.timestampLastUpdate = new Timestamp(System.currentTimeMillis());
    }

    public Player(@NotNull String name, int score, Timestamp timestampSubscription){
        this.name = name;
        this.score = score;
        this.timestampLastUpdate = new Timestamp(System.currentTimeMillis());
        this.timestampSubscription = timestampSubscription;
    }

    //busca
    public Player(Long id, String name, int score, Timestamp timestampSubscription, Timestamp timestampLastUpdate) {
        this.name = name;
        this.score = score;
        this.timestampLastUpdate = timestampLastUpdate;
        this.timestampSubscription = timestampSubscription;
        this.id = id;
    }

    //update no BD via JDBC
    public Player(int id, String name, int score){
        this.name = name;
        this.score = score;
        this.timestampLastUpdate = new Timestamp(System.currentTimeMillis());
        this.timestampSubscription = null; //buscar no BD
        this.id = (long) id;
    }

    //delete no BD
    public Player(String name){
        this.name = name;
        this.timestampLastUpdate = null; //buscar no bd
        this.timestampSubscription = null; //buscar no bd

    }

    //recebendo a requisição post
    public Player(String name, int score){
        this.name = name;
        this.score = score;
        this.timestampLastUpdate = new Timestamp(System.currentTimeMillis());
        this.timestampSubscription = new Timestamp(System.currentTimeMillis());
    }

    //update via JPA
    public Player (Long id, int score){
        this.id = id;
        this.score = score;
        this.timestampLastUpdate = new Timestamp(System.currentTimeMillis());
        this.timestampSubscription = null;
    }

    public Timestamp getTimestampSubscription() {
        return timestampSubscription;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public boolean isEmpty(){
        return this.name == null;
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
    }
}