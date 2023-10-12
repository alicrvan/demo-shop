package io.openstech.user_managment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class CustomException {

    //SQLIntegrityConstraintViolationException

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> notValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getFieldError().getField()
                        + ": " + ex.getFieldError().getDefaultMessage());
    }



    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> notValidInputException(ConstraintViolationException ex) {
        List<String> messages = new ArrayList<>();

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            messages.add(violation.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(String.join(", ", messages));
    }



    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> productNotFound(CustomerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }


    @ExceptionHandler(CustomerAlreadyExistException.class)
    public ResponseEntity<String> productNotFound(CustomerAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
