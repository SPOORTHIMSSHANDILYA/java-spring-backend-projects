package com.acc.common_lib.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements Serializable {
    private static final long serialVersionUID = 1L;

    private  int code;
    private  String status;
    private  Object data;
    private  String message;

    public Response(HttpStatus code, String status) {
        this.code = code.value();
        this.status = status;
    }

    public Response(HttpStatus code, String status, Object data) {
        this.code = code.value();
        this.status = status;
        this.data = data;
    }

    public Response(HttpStatus code, String status, String message) {
        this.code = code.value();
        this.status = status;
        this.message = message;
    }

    public Response(HttpStatus code, String status, Object data, String message) {
        this.code = code.value();
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
