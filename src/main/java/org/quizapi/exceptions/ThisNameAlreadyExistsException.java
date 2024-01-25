package org.quizapi.exceptions;

public class ThisNameAlreadyExistsException extends Exception{
    public ThisNameAlreadyExistsException(){
    }

    public ThisNameAlreadyExistsException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}

