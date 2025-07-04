package main.java.com.ecommerce.models;

import main.java.com.ecommerce.interfaces.Shippable;
import main.java.com.ecommerce.models.Product;

public class PhysicalProduct extends Product implements Shippable {
    private double weight;

    public PhysicalProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public boolean requiresShipping() {
        return true;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
