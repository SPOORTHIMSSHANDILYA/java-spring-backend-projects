package com.acc.common_lib.models;

import lombok.Data;
import org.springframework.http.HttpStatus;
import java.io.Serializable;

@Data
public class UnauthorizedResponse extends Response implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final HttpStatus DEFAULT_CODE = HttpStatus.UNAUTHORIZED;
    private static final String DEFAULT_STATUS = "Error";

    public UnauthorizedResponse(){
        super(DEFAULT_CODE,DEFAULT_STATUS);
    }

    public UnauthorizedResponse(String message){
        super(DEFAULT_CODE,DEFAULT_STATUS,message);
    }

    public UnauthorizedResponse(Object data){
        super(DEFAULT_CODE,DEFAULT_STATUS,data);
    }

    public UnauthorizedResponse(String message, Object data){
        super(DEFAULT_CODE,DEFAULT_STATUS,data,message);
    }

    public UnauthorizedResponse(HttpStatus code, String message){
        super(code,message);
    }
}

