package com.bmutinda.httpbuster;

import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

public class BusterResponse {
    Response response;
    String string;
    ResponseBody body;

    public BusterResponse( Response response ){
        this.response = response;
        readBody();
        readString();
    }

    private void readBody(){
        if ( response == null ){
            return;
        }

        this.body = response.body();
    }

    private void readString(){
        if ( body ==null ){
            return;
        }

        try{
            this.string = response.body().string();
        }catch (Exception e){
            HttpBuster.log("Okhttp string() is alread called... empty body initialized");
        }
    }

    public Response getResponse(){
        return response;
    }

    public ResponseBody getBody(){
        return body;
    }

    public String getString(){
        return this.string;
    }

    public static BusterResponse build( Response response ){
        return new BusterResponse(response);
    }
}