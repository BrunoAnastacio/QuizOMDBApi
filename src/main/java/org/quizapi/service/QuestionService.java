
package org.quizapi.service;

import org.quizapi.domain.question.Question;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionService {
    @GetMapping("/newQuestion")
    public String getNewQuestion() {
        try{
            Question question = new Question();
            return question.toJson();
        }catch(Exception e){
            return "Erro";
        }
   }




}
