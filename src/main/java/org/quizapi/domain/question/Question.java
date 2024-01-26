package org.quizapi.domain.question;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.quizapi.domain.title.Title;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {

    private final String plot; //enunciado
    private final List<String> options = new ArrayList<>(); //multiplas escolhas
    private final String rightTitle; //resposta correta


    public Question() throws InterruptedException {

        Title title = new Title(true);

        this.plot = title.getPlot();
        this.rightTitle = title.getName();
        this.options.add(this.rightTitle);

        for(int i = 0; i<4; i++){
            Title getWrongOptions = new Title(false);
            this.options.add(getWrongOptions.getName());
        }

        Collections.shuffle(options);
        //System.out.println("Questão criada");

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
    public String toJson() throws InterruptedException {
        Question question = new Question();
        Gson gson = new GsonBuilder().create();
        return gson.toJson(question);

    }
}