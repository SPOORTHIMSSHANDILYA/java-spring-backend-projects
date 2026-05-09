package com.acc.common_lib.models;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
public class SuccessResponse extends Response implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final HttpStatus DEFAULT_CODE = HttpStatus.OK;
    private static final String DEFAULT_STATUS = "Success";

    public SuccessResponse(){
        super(DEFAULT_CODE,DEFAULT_STATUS);
    }

    public SuccessResponse(String message){
        super(DEFAULT_CODE,DEFAULT_STATUS,message);
    }

    public SuccessResponse(Object data){
        super(DEFAULT_CODE,DEFAULT_STATUS,data);
    }

    public SuccessResponse(String message, Object data){
        super(DEFAULT_CODE,DEFAULT_STATUS,data,message);
    }

    public SuccessResponse(HttpStatus code, String message){
        super(code,message);
    }
}
