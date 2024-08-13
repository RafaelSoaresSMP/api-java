package com.eventos.eventos.infra;

import com.eventos.eventos.exceptions.EventNotFoundException;
import com.eventos.eventos.exceptions.UserExistException;
import com.eventos.eventos.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserExistException.class)
    private ResponseEntity<String> userNotFoundHandler(UserExistException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User exists in application");
    }
}
