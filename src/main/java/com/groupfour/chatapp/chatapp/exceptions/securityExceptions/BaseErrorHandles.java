package com.groupfour.chatapp.chatapp.exceptions.securityExceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

    import javax.xml.bind.ValidationException;

@ControllerAdvice
    @Slf4j
    public class BaseErrorHandles {

        @ResponseBody
        @ExceptionHandler(value = ValidationException.class)
        public ResponseEntity<?> handleException(ValidationException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

