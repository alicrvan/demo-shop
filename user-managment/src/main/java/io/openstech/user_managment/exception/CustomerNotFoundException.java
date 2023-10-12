package io.openstech.user_managment.exception;

public class CustomerNotFoundException extends RuntimeException{

public CustomerNotFoundException(String name){

    super("Customer with"+ name + "not found!");

}


}
