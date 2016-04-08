
/*
 * Copyright (c) 2016  Mutinda Boniface
 *
 * <http://bmutinda.com | boniface.info@gmail.com>
 */

package com.bmutinda.httpbuster.files;

import com.squareup.okhttp.MediaType;

import java.io.File;

public class RequestFile {
    String fileKey="file";
    String filePath;
    MediaType mediaType;

    public RequestFile(){
    }

    public RequestFile(String fileKey, String filePath, MediaType mediaType){
        this.fileKey = fileKey;
        this.filePath = filePath;
        this.mediaType = mediaType;
    }

    public RequestFile setFileKey( String fileKey ){
        this.fileKey = fileKey;
        return this;
    }

    public RequestFile setFilePath( String filePath ){
        this.filePath = filePath;
        return this;
    }

    public RequestFile setMediaType( MediaType mediaType ){
        this.mediaType = mediaType;
        return this;
    }

    public String getFileKey(){
        return this.fileKey;
    }
    public String getFileName(){
        if (getFilePath() ==null){
            return  "";
        }

        File f = new File(getFilePath());
        return f.getName();
    }

    public String getFilePath(){
        return this.filePath;
    }

    public MediaType getMediaType(){
        return this.mediaType;
    }
}