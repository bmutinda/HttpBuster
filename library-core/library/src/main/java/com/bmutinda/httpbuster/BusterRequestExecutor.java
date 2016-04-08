package com.bmutinda.httpbuster;

import com.bmutinda.httpbuster.files.RequestFile;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BusterRequestExecutor {
    HttpBuster httpBuster;
    BusterRequest request;

    public BusterRequestExecutor(HttpBuster httpBuster, BusterRequest request){
        this.httpBuster = httpBuster;
        this.request = request;
    }

    public void run( final ApiCallback apiCallback ){
        if ( httpBuster == null || request == null ){
            apiCallback.done(null, new Exception("HttpBuster or request is empty."));
            return;
        }

        Request httpRequest = null;
        String finalUrl = httpBuster.generateUrl(request.getUrl());
        if (request.getMethod().equals(ApiMethod.GET)){
            StringBuilder sb = new StringBuilder();
            if ( request.getParams()!=null ){
                for (Map.Entry<String, Object> entry: request.getParams().entrySet() ){
                    sb.append( (String.valueOf(entry.getKey())+"="+String.valueOf(entry.getValue() ))+"&" );
                }
            }
            finalUrl+="?"+sb.toString();
            httpRequest = prepareRequest(finalUrl).build();

        }else if ( request.getMethod().equals(ApiMethod.POST)){
            FormEncodingBuilder formBuilder = new FormEncodingBuilder();
            if ( request.getParams()!=null ){
                for (Map.Entry<String, Object> entry : request.getParams().entrySet()) {
                    formBuilder.add(entry.getKey(), String.valueOf(entry.getValue()));
                }
            }
            Request.Builder builder = prepareRequest(finalUrl);
            httpRequest = builder.post( formBuilder.build() ).build();

        }else if ( request.getMethod().equals(ApiMethod.DELETE)){
            FormEncodingBuilder formBuilder = new FormEncodingBuilder();
            if ( request.getParams()!=null ){
                for (Map.Entry<String, Object> entry : request.getParams().entrySet()) {
                    formBuilder.add(entry.getKey(), String.valueOf(entry.getValue()));
                }
            }
            Request.Builder builder = prepareRequest(finalUrl);
            httpRequest = builder.delete( formBuilder.build() ).build();

        }else if ( request.getMethod().equals(ApiMethod.MULTIPART)){
            MultipartBuilder multipartBuilder = new MultipartBuilder();
            multipartBuilder.type(MultipartBuilder.FORM);

            if ( request.getParams()!=null ){
                for (Map.Entry<String, Object> entry : request.getParams().entrySet()) {
                    multipartBuilder.addFormDataPart(entry.getKey(), String.valueOf(entry.getValue()));
                }
            }

            MultipartRequest req = (MultipartRequest) request;
            for (RequestFile requestFile: req.getRequestFiles()){
                multipartBuilder.addFormDataPart(requestFile.getFileKey(), requestFile.getFileName(), RequestBody.create(requestFile.getMediaType(), new File(requestFile.getFileName())));
            }
            Request.Builder builder = prepareRequest(finalUrl);
            httpRequest = builder.post( multipartBuilder.build() ).build();
        }

        if ( httpRequest == null ){
            apiCallback.done(null, new Exception("Un-known request method supplied."));
            return;
        }

        HttpBuster.log("Making a request to "+finalUrl+", type = "+request.getMethod().toString());

        httpBuster.getHttpClient().newCall( httpRequest ).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                apiCallback.done( null, e );
            }

            @Override
            public void onResponse(Response response) throws IOException {
                JSONObject json = new JSONObject();
                Exception exception = null;
                try {
                    json = new JSONObject( response.body().string() );
                } catch (JSONException e) {
                    exception = e ;
                }
                apiCallback.done(json, exception);
            }
        });
    }

    private Request.Builder prepareRequest( String url ){
        Request.Builder requestBuilder = new Request.Builder().url(url);
        List<ApiHeader> headers = httpBuster.getApi().getHeaders();
        for( ApiHeader entity: headers ){
            requestBuilder.addHeader( entity.getKey(), String.valueOf( entity.getValue()) );
        }
        return requestBuilder;
    }

    public static void execute( HttpBuster httpBuster, BusterRequest request, ApiCallback apiCallback){
        // Add default api params
        List<ApiRequestParam> defaultParams = httpBuster.getApi().getDefaultParams();
        for ( ApiRequestParam apiRequestParam: defaultParams){
            request.addParam(apiRequestParam);
        }
        new BusterRequestExecutor(httpBuster, request).run(apiCallback);
    }
}