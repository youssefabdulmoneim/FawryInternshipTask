package main.java.com.ecommerce.models;

public class DigitalProduct extends Product {
    public DigitalProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public boolean requiresShipping() {
        return false;
    }
}