package com.bmutinda.httpbuster;

import android.app.Application;
import android.content.Context;

import com.bmutinda.httpbuster.api.Api;
import com.bmutinda.httpbuster.api.ApiHeader;
import com.bmutinda.httpbuster.api.ApiRequestParam;
import com.bmutinda.httpbuster.api.ConfigKey;
import com.bmutinda.httpbuster.api.Configuration;
import com.bmutinda.httpbuster.api.HttpBuster;

import java.io.File;

public class CustomApplication extends Application {

    public static HttpBuster httpBuster;
    public static HttpBuster httpBuster2;

    @Override
    public void onCreate(){
        super.onCreate();

        Api api = new Api();
        api.setEndpoint("http://192.168.0.157/tests/dummyapi/");
        api.addDefaultParam(new ApiRequestParam("user", "111"));

        // 2. Advanced with custom configs
        Configuration configuration = Configuration.create();
        configuration.add(ConfigKey.CONNECTION_TIMEOUT, 30);
        configuration.add(ConfigKey.READ_TIMEOUT, 30);
        configuration.add(ConfigKey.WRITE_TIMEOUT, 30);
        httpBuster = HttpBuster.withApi(api)
                .withConfiguration(configuration)
                .build();
        // enable cache
        File cacheDirectory = getDir("MyAppDirectoryPath", Context.MODE_PRIVATE);
        int cacheSize = 50 * 1024 * 1024; // 50 MiB
        httpBuster.enableCache(cacheDirectory, cacheSize);

        // Another instance
        Api api2 = new Api();
        api2.setEndpoint("http://192.168.0.157/tests/dummyapi/");
        api2.addHeader(new ApiHeader("Authorization", "AUTH-KEY-HERE"));
        api2.addDefaultParam(new ApiRequestParam("user", "111"));
        httpBuster2 = HttpBuster.withApi(api2).build();
    }
}
