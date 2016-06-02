package com.bmutinda.httpbuster.demo;

import android.app.Application;

import com.bmutinda.httpbuster.Api;
import com.bmutinda.httpbuster.HttpBuster;

public class HttpBusterApplication extends Application {

    static HttpBuster httpBuster;

    @Override
    public void onCreate(){
        super.onCreate();

        initializeApi();
    }

    private void initializeApi(){
        Api api = new Api();
        api.setEndpoint("https://api.icndb.com/");
        httpBuster = HttpBuster.withApi(api).build();
    }

    public static HttpBuster getHttpBuster(){
        return httpBuster;
    }

}