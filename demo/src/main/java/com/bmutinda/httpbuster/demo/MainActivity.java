package com.bmutinda.httpbuster.demo;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bmutinda.httpbuster.ApiCallback;
import com.bmutinda.httpbuster.BusterResponse;
import com.bmutinda.httpbuster.files.RequestFile;
import com.squareup.okhttp.MediaType;
import com.vistrav.ask.Ask;
import com.vistrav.ask.annotations.AskGrantedAll;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ask.on(this)
                .forPermissions(Manifest.permission.READ_EXTERNAL_STORAGE
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withRationales("READ",
                        "READ")
                .go();
    }

    @AskGrantedAll
    public void grantedAll(){
        runGet();
        runPost();
        runPostUpload();
        runPut();
        runDelete();
    }

    private void runGet(){
        // Get Request without any params added
        HttpBusterApplication.getHttpBuster().makeGetRequest("post/1", null, new ApiCallback() {
            @Override
            public void done(BusterResponse response, JSONObject jsonObject, Exception exception) {
                log( "GET - Response NO-PARAMS =" +(response!=null? response.getString() :"Not reachable" ));
            }
        });

        // Get Request with other parameters added
        HashMap<String, Object> map = new HashMap<>();
        map.put("orderBy", "date");
        HttpBusterApplication.getHttpBuster().makeGetRequest("posts/", map, new ApiCallback() {
            @Override
            public void done(BusterResponse response, JSONObject jsonObject, Exception exception) {
                log( "GET - Response NO-PARAMS =" +(response!=null? response.getString() :"Not reachable" ));

            }
        });
    }

    private void runPost(){

        HashMap<String, Object> map = new HashMap<>();
        map.put("title", "new title is here");
        HttpBusterApplication.getHttpBuster().makePostRequest("post/1/", map, new ApiCallback() {
            @Override
            public void done(BusterResponse response, JSONObject jsonObject, Exception exception) {
                log(  "POST - Response =" +(response!=null? response.getString() :"Not reachable" ));
            }
        });

    }

    private void runPostUpload(){
        String file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath()+"/images.jpeg";

        RequestFile requestFile = new RequestFile("photo", file, MediaType.parse("image/jpeg"));
        List<RequestFile> files = new ArrayList<>();
        files.add( requestFile );

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Mutinda Boniface");

        HttpBusterApplication.getHttpBuster().makeMultipartRequest("photo-upload/", map, files, new ApiCallback() {
            @Override
            public void done(BusterResponse response, JSONObject jsonObject, Exception exception) {

                if ( exception !=null ){
                    exception.printStackTrace();
                    log( "Excepti===" +exception.getLocalizedMessage());
                }

                log(  "POST MULTIPART - Response =" +(response!=null? response.getString() :"Not reachable" ));
            }
        });

    }

    private void runPut(){

    }

    private void runDelete(){

    }


    private static void log( String message ){
        Log.e(TAG, message);
        System.out.print(message);
    }
}
