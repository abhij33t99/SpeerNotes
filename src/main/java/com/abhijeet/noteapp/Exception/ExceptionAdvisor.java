package com.abhijeet.noteapp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> badCredentialsException(BadCredentialsException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoNotesFoundException.class)
    public ResponseEntity<String> noNotesFoundException(NoNotesFoundException noNotesFoundException){
        return new ResponseEntity<>(noNotesFoundException.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> usernameNotFoundException(UsernameNotFoundException usernameNotFoundException){
        return new ResponseEntity<>(usernameNotFoundException.getMessage(), HttpStatus.OK);
    }
}
