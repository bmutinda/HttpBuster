package com.bmutinda.httpbuster;

public class ApiHeader {
    String key;
    String value;

    public ApiHeader(){
    }

    public ApiHeader(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey(){
        return key;
    }
    public String getValue(){
        return value;
    }
}