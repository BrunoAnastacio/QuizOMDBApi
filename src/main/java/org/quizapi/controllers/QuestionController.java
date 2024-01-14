
package org.quizapi.controllers;

import org.quizapi.models.beans.Question;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class QuestionController {
    @GetMapping("/newQuestion")
    public String getNewQuestion() throws IOException, InterruptedException {

        try{
            Question question = new Question();
            return question.toJson();
        }catch(Exception e){
            return "Erro";
        }
   }




}