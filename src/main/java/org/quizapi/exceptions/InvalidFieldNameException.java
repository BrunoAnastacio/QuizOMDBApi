package org.quizapi.exceptions;

public class InvalidFieldNameException extends Exception{
    public InvalidFieldNameException() {
    }

    public InvalidFieldNameException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}