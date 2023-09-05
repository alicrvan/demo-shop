package io.techmo.product_managment.excpetion;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CustomExceptionHandler {


//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(Exception ex) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body("It does not work properly");
//    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> notValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getFieldError().getField()
                       + ": " + ex.getFieldError().getDefaultMessage());
                }


    @ExceptionHandler(ProductAlreadyExistException.class)
    public ResponseEntity<String> productExists(ProductAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

}
