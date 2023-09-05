package io.techmo.product_managment.excpetion;

public class ProductNotFoundException extends RuntimeException{


    public ProductNotFoundException() {
        super("Product Not Found");
    }


}
