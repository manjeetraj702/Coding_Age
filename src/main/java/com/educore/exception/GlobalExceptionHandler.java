package com.educore.exception;


import com.educore.common.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Handling runtime exceptions
    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<Object> runtimeExceptionHandler(RuntimeException e){
        return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public ApiResponse<Object> nullPointerExceptionHandler(NullPointerException e){
        return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // Handling Unknown exceptions
    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> generalExceptionHandler(Exception e){
        return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) // Handling Application exceptions
    @ExceptionHandler(ApplicationException.class)
    public ApiResponse<Object> applicationExceptionHandler(ApplicationException e){
        return new ApiResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) // Handling Validation annotations exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Object> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ApiResponse<>(errors, HttpStatus.BAD_REQUEST, "Validation Failed");
    }

    @ResponseStatus(HttpStatus.FORBIDDEN) // Handling Authorization exceptions
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ApiResponse<Object> handleAuthorizationDeniedException(AuthorizationDeniedException e) {
        return new ApiResponse<>(HttpStatus.FORBIDDEN, "Access Denied");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) // Handling Illegal Argument exceptions
    @ExceptionHandler(IllegalArgumentException.class) 
    public ApiResponse<Object> badRequestExceptionHandler(IllegalArgumentException e){
        return new ApiResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
    }

}