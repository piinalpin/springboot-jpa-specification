package com.piinalpin.queryrequest.Exception.Advice;

import com.piinalpin.queryrequest.Exception.InvalidDataTypeException;
import com.piinalpin.queryrequest.Exception.KeyNotFoundException;
import com.piinalpin.queryrequest.domain.dto.SecurityResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidDataTypeAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidDataTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    SecurityResponse invalidDataTypeExceptionHandler(InvalidDataTypeException ex) {
        SecurityResponse response = new SecurityResponse(ex.getMessage());
        return response;
    }
}
