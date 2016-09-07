package com.bmutinda.httpbuster.demo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bmutinda.httpbuster.ApiCallback;
import com.bmutinda.httpbuster.BusterResponse;
import com.bmutinda.httpbuster.files.RequestFile;
import com.squareup.okhttp.MediaType;

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
        String file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath()+"/no_picture.png";

        RequestFile requestFile = new RequestFile("photo", file, MediaType.parse("image/PNG"));
        List<RequestFile> files = new ArrayList<>();
        files.add( requestFile );

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Mutinda Boniface");

        HttpBusterApplication.getHttpBuster().makeMultipartRequest("photo-upload/", map, files, new ApiCallback() {
            @Override
            public void done(BusterResponse response, JSONObject jsonObject, Exception exception) {
                exception.printStackTrace();

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
