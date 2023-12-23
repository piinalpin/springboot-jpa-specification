package com.piinalpin.queryrequest.Exception.Advice;

import com.piinalpin.queryrequest.Exception.KeyNotFoundException;
import com.piinalpin.queryrequest.domain.dto.SecurityResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class KeyNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(KeyNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    SecurityResponse keyNotFoundExceptionHandler(KeyNotFoundException ex) {
        SecurityResponse response = new SecurityResponse(ex.getMessage());
        return response;
    }
}
