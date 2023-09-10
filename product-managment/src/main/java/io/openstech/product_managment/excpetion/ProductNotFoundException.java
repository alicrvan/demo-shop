package io.openstech.product_managment.excpetion;

public class ProductNotFoundException extends RuntimeException{


    public ProductNotFoundException() {
        super("Product Not Found");
    }


}
