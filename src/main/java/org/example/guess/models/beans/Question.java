
package org.example.guess.models.beans;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {

    private String plot; //enunciado
    private List<String> options = new ArrayList<>(); //multiplas escolhas
    private String rightTitle; //resposta correta


    public Question() throws IOException, InterruptedException {

        Title title = new Title(true);

        this.plot = title.getPlot();
        this.rightTitle = title.getName();
        this.options.add(this.rightTitle);

        for(int i = 0; i<4; i++){
            Title getWrongOptions = new Title(false);
            this.options.add(getWrongOptions.getName());
        }

        Collections.shuffle(options);
        System.out.println("Questão criada");

    }

    public String getPlot() {
        return plot;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getOptions(int i){
        return options.get(i);
    }

    public String getRightTitle() {
        return rightTitle;
    }

    @Override
    public String toString() {
        return "Question{" +
                "plot='" + plot + '\'' +
                ", options=" + options +
                ", rightTitle='" + rightTitle + '\'' +
                '}';
    }
    public String toJson() throws IOException, InterruptedException {
        Question question = new Question();
        Gson gson = new GsonBuilder().create();
        return gson.toJson(question);

    }
}