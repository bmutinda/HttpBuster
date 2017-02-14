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
        api.setEndpoint("http://10.0.2.2/apps/httpbuster/api/v1/");
        httpBuster = HttpBuster.withApi(api)
                .enableLogs(true)
                .build();
    }

    public static HttpBuster getHttpBuster(){
        return httpBuster;
    }

}