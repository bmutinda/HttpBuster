package com.bmutinda.httpbuster.api.exceptions;

public class ConfigurationException extends Exception {
    public ConfigurationException(){
        super();
    }
    public ConfigurationException(String message){
        super(message);
    }
}