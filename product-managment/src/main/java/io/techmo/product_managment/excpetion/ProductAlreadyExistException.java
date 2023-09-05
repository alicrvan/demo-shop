package io.techmo.product_managment.excpetion;

public class ProductAlreadyExistException extends  RuntimeException{

    public ProductAlreadyExistException() {
        super("Product Already Exist! please try to modify product instead of adding new one");
    }
}
