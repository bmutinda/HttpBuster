package com.bmutinda.httpbuster;

public class ApiRequestParam {
    String key;
    String value;

    public ApiRequestParam( String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey(){
        return this.key;
    }

    public String getValue( ){
        return this.value;
    }
}