
package com.bmutinda.httpbuster;

import com.bmutinda.httpbuster.files.RequestFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MultipartRequest extends BusterRequest {

    List<RequestFile> requestFiles;

    public MultipartRequest(){
        super();
        this.method = ApiMethod.MULTIPART;
        requestFiles = new ArrayList<>();
    }

    public MultipartRequest(String url){
        this();
        this.url = url;
    }

    public MultipartRequest(String url, HashMap<String, Object> params){
        this();
        this.url = url;
        addParams( params );
    }

    public MultipartRequest(String url, HashMap<String, Object> params, List<RequestFile> requestFiles){
        this();
        this.url = url;
        this.requestFiles = requestFiles;
        addParams( params );
    }

    protected MultipartRequest addRequestFiles( List<RequestFile> requestFiles ){
        if ( requestFiles !=null ){
            this.requestFiles.addAll(requestFiles);
        }
        return this;
    }

    protected MultipartRequest addFile( RequestFile requestFile ){
        if (requestFile !=null ){
            this.requestFiles.add(requestFile);
        }
        return this;
    }

    public List<RequestFile> getRequestFiles(){
        return this.requestFiles;
    }
}