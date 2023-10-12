package io.openstech.user_managment.exception;

public class CustomerAlreadyExistException extends RuntimeException{

     public CustomerAlreadyExistException(){
        super("Customer with same User name already exist");
    }
}
