package com.acc.common_lib.models;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
public class ErrorResponse extends Response implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final HttpStatus DEFAULT_CODE = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final String DEFAULT_STATUS = "Error";

    public ErrorResponse(){
        super(DEFAULT_CODE,DEFAULT_STATUS);
    }

    public ErrorResponse(String message){
        super(DEFAULT_CODE,DEFAULT_STATUS,message);
    }

    public ErrorResponse(Object data){
        super(DEFAULT_CODE,DEFAULT_STATUS,data);
    }

    public ErrorResponse(String message, Object data){
        super(DEFAULT_CODE,DEFAULT_STATUS,data,message);
    }

    public ErrorResponse(HttpStatus code, String message, Object data){
        super(code, "Error", data, message);
    }

    public ErrorResponse(HttpStatus code, String message){
        super(code,message);
    }
}

