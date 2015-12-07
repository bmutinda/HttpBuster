package com.bmutinda.httpbuster.api;

import java.util.HashMap;
import java.util.List;

public class BusterRequest {
    protected String url;
    protected ApiMethod method;
    protected HashMap<String, Object> params;

    public BusterRequest(){
        params = new HashMap<>();
    }

    protected BusterRequest setUrl( String url ){
        this.url = url;
        return this;
    }

    protected BusterRequest addParams( HashMap<String, Object> params ){
        if ( params !=null ){
            this.params.putAll(params);
        }
        return this;
    }

    protected BusterRequest addParams( List<ApiRequestParam> apiRequestParams ){
        for ( ApiRequestParam apiRequestParam: apiRequestParams){
            addParam(apiRequestParam);
        }
        return this;
    }

    protected BusterRequest addParam( ApiRequestParam apiRequestParam ){
        if ( apiRequestParam !=null ){
            this.params.put(apiRequestParam.getKey(), apiRequestParam.getValue());
        }
        return this;
    }

    public String getUrl(){
        return this.url;
    }

    public ApiMethod getMethod(){
        return this.method;
    }

    public HashMap<String, Object> getParams(){
        return this.params;
    }
}