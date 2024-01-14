package org.quizapi.exceptions;

public class ProxyBlockException extends Exception{
    public ProxyBlockException() {
    }

    public ProxyBlockException(String message) {
        super(message);
    }

    public String getMessage(){
        //return super.getMessage();
        return this.getMessage();
    }

}
