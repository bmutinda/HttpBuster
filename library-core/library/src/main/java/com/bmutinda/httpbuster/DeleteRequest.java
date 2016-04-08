
package com.bmutinda.httpbuster;

import java.util.HashMap;

public class DeleteRequest extends BusterRequest {

    public DeleteRequest(){
        super();
        this.method = ApiMethod.DELETE;
    }

    public DeleteRequest(String url){
        this();
        this.url = url;
    }

    public DeleteRequest(String url, HashMap<String, Object> params){
        this();
        this.url = url;
        addParams( params );
    }
}