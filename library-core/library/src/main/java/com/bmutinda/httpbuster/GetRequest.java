package com.bmutinda.httpbuster;

import java.util.HashMap;

public class GetRequest extends BusterRequest {

    public GetRequest(){
        super();
        this.method = ApiMethod.GET;
    }

    public GetRequest(String url){
        this();
        this.url = url;
    }

    public GetRequest(String url, HashMap<String, Object> params){
        this();
        this.url = url;
        addParams(params);
    }
}