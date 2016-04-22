/*
 * Copyright (c) 2016  Mutinda Boniface
 *
 * <http://bmutinda.com | boniface.info@gmail.com>
 */

package com.bmutinda.httpbuster;

import java.util.ArrayList;
import java.util.List;

public class Api {
    List<ApiHeader> headers;
    List<ApiRequestParam> defaultParams;
    String endpoint = null;

    public Api(){
        headers = new ArrayList<>();
        defaultParams = new ArrayList<>();
    }

    public Api setEndpoint( String url ){
        this.endpoint = url;
        return this;
    }

    public Api addHeaders( List<ApiHeader> headers){
        for (ApiHeader apiHeader: headers){
            addHeader(apiHeader);
        }
        return this;
    }

    public Api addHeader( ApiHeader header ){
        boolean alreadyAdded = false;
        if ( header !=null ){
            for( ApiHeader apiHeader: this.headers){
                if ( apiHeader.getKey().equals(header.getKey())){
                    // update its value
                    apiHeader.value = header.getValue();
                    alreadyAdded = true;
                    break;
                }
            }
        }
        if ( ! alreadyAdded){
            this.headers.add(header);
        }
        return this;
    }

    public Api addDefaultParams( List<ApiRequestParam> apiRequestParams){
        this.defaultParams.addAll(apiRequestParams);
        return this;
    }

    public Api addDefaultParam( ApiRequestParam param ){
        this.defaultParams.add(param);
        return this;
    }

    public String getEndpoint(){
        return this.endpoint;
    }

    public List<ApiHeader> getHeaders(){
        return this.headers;
    }

    public List<ApiRequestParam> getDefaultParams(){
        return this.defaultParams;
    }
}