package com.example.distributedcache.configs;

import com.example.distributedcache.exceptions.NotFoundException;
import com.example.distributedcache.wrappers.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody public ApiError requestHandlingNoHandlerFound() {
        return new ApiError("404", "Value not found for the Key");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody public ApiError requestHandlingNoHandlerFound(Exception ex) {
        return new ApiError("500", ex.getMessage());
    }
}
