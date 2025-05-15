package com.educore.common.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiResponse<T> {
    private T data;
    private int status;
    private String message;

    public ApiResponse(T data, HttpStatus status, String message){
        this.data = data;
        this.status = status.value();
        this.message = message;
    }
    public ApiResponse(HttpStatus status, String message){
        this.status = status.value();
        this.message = message;
    }
    public ApiResponse(T data, HttpStatus status){
        this.data = data;
        this.status = status.value();
    }
}
