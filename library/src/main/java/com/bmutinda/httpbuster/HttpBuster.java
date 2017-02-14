/*
 * Copyright (c) 2016  Mutinda Boniface
 *
 * <http://bmutinda.com | boniface.info@gmail.com>
 */

package com.bmutinda.httpbuster;

import android.util.Log;

import com.bmutinda.httpbuster.files.RequestFile;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HttpBuster {
    private OkHttpClient okHttpClient;
    private Api api;
    private Configuration configuration;
    public static boolean debug = false;

    private HttpBuster(Api api){
        this.api = api;
    }

    public HttpBuster enableLogs( boolean enable ){
        debug = enable;
        return this;
    }

    public HttpBuster withConfiguration(Configuration configuration ){
        this.configuration = configuration;
        return this;
    }

    public HttpBuster build(){
        if ( configuration == null ){
            configuration = new Configuration();
        }
        createHttpClient();
        return this;
    }

    private OkHttpClient createHttpClient(){
        if ( okHttpClient !=null ){
            log("Oops! okHttp client seems to be already created.");
            return okHttpClient;
        }

        if ( configuration == null ){
            log("Configuration seems to be empty. Pass an non-empty configuration object");
            return null;
        }

        okHttpClient =  new OkHttpClient();
        okHttpClient.setConnectTimeout(configuration.getConnectionTimeout(), TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(configuration.getWriteTimeout(), TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(configuration.getReadTimeout(), TimeUnit.SECONDS);

        return okHttpClient;
    }

    public boolean enableCache( File cacheDirectory, int cacheSize ){
        if ( okHttpClient ==null || cacheDirectory == null ){
            return false;
        }
        Cache cache = new Cache(cacheDirectory, cacheSize);
        okHttpClient.setCache(cache);
        return true;
    }

    public OkHttpClient getHttpClient(){
        return okHttpClient;
    }
    public Api getApi(){
        return api;
    }

    /**
     * Generate url with the endpoint prepended to it
     *
     * @param url - the url to append to the endpoint
     * @return
     */
    public String generateUrl( String url ){
        if ( api.getEndpoint() == null || url ==null ){
            return url;
        }
        if ( url.contains( api.getEndpoint())){
            return url;
        }
        return String.format("%s%s%s",
                api.getEndpoint(), api.getEndpoint().endsWith("/") ? "" : "/", url.startsWith("/") ? url.substring(1, url.length()) : url );
    }

    // =====================
    // REQUESTS stuff
    // =====================

    public void makeGetRequest( String url, HashMap<String, Object> params, ApiCallback apiCallback ){
        GetRequest getRequest = new GetRequest(url, params);
        BusterRequestExecutor.execute(this, getRequest, apiCallback);
    }

    public void makePostRequest( String url, HashMap<String, Object> params, ApiCallback apiCallback ){
        PostRequest postRequest = new PostRequest(url, params);
        BusterRequestExecutor.execute(this, postRequest, apiCallback);
    }

    public void makeDeleteRequest( String url, HashMap<String, Object> params, ApiCallback apiCallback ){
        DeleteRequest deleteRequest = new DeleteRequest(url, params);
        BusterRequestExecutor.execute(this, deleteRequest, apiCallback);
    }

    public void makeMultipartRequest( String url, HashMap<String, Object> params, List<RequestFile> requestFiles, ApiCallback apiCallback ){
        MultipartRequest multipartRequest = new MultipartRequest(url, params, requestFiles);
        BusterRequestExecutor.execute(this, multipartRequest, apiCallback);
    }

    public static HttpBuster withApi( Api api ){
        return new HttpBuster(api);
    }

    public static void log( String message ){
        if ( ! debug ){
            return;
        }
        Log.e("HttpBuster", message);
    }
}