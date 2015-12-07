package com.bmutinda.httpbuster.api;

import java.util.HashMap;

public class PostRequest extends BusterRequest {

    public PostRequest(){
        super();
        this.method = ApiMethod.POST;
    }

    public PostRequest(String url){
        this();
        this.url = url;
    }

    public PostRequest(String url, HashMap<String, Object> params){
        this();
        this.url = url;
        addParams( params );
    }
}