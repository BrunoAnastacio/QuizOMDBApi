package org.quizapi.util.exceptions;

public class NotFoundIDException extends Exception{
    public NotFoundIDException(){
    }

    public NotFoundIDException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}