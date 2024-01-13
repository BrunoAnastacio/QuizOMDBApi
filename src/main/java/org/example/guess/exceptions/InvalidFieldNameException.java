package org.example.guess.exceptions;

public class InvalidFieldNameException extends Exception{
    public InvalidFieldNameException() {
    }

    public InvalidFieldNameException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return this.getMessage();
        //return super.getMessage();
    }
}
