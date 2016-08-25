package controller;

import exception.UserException;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by swatch on 8/23/16.
 */
@RestController
@ControllerAdvice
public class ExceptionHandlerController{
    @ExceptionHandler(UserException.class)
    public HttpEntity<Resource<String>> handleUserFormatException(UserException e) {
        return new ResponseEntity<>(new Resource<String>(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NullPointerException.class)
    public HttpEntity<Resource<String>> handleNullPointerException(UserException e) {
        return new ResponseEntity<>(new Resource<String>(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
