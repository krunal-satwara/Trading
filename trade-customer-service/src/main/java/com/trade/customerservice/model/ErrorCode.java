package com.trade.customerservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorCode {

    private Integer statusCode;
    private String message;
    private List<?> jsonBody;
    private Object object;

    public ErrorCode(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ErrorCode(Integer statusCode, String message, List<?> jsonBody) {
        this.statusCode = statusCode;
        this.message = message;
        this.jsonBody = jsonBody;
    }

    public ErrorCode(Integer statusCode, String message, Object object) {
        this.statusCode = statusCode;
        this.message = message;
        this.object = object;
    }

    public ErrorCode(String message) {
        this.message = message;
    }
}
