package com.gurkha.exception;

public class FileNotFound extends  RuntimeException {
    public  FileNotFound(String message){
        super(message);
    }
}
