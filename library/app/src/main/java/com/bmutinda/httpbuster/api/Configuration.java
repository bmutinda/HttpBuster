package com.bmutinda.httpbuster.api;

public class Configuration {

    private int connectionTimeout = 30;
    private int writeTimeout = 30;
    private int readTimeout = 30;

    public Configuration(){
    }

    public static Configuration create(){
        return new Configuration();
    }

    public Configuration add( ConfigKey key, int val ){
        if (key.equals(ConfigKey.CONNECTION_TIMEOUT)){
            this.connectionTimeout = val;
        }
        else if (key.equals(ConfigKey.WRITE_TIMEOUT)){
            this.writeTimeout = val;
        }
        else if (key.equals(ConfigKey.READ_TIMEOUT)){
            this.readTimeout = val;
        }
        return this;
    }

    public int getConnectionTimeout(){ return connectionTimeout; }
    public int getReadTimeout(){ return readTimeout; }
    public int getWriteTimeout(){ return writeTimeout; }


    @Override
    public String toString(){
        return String.format(
                "ConnectionTimeout=%s\n" +
                "WriteTimeout=%s\n" +
                "ReadTimeout=%s", connectionTimeout, writeTimeout, readTimeout);
    }
}