package main.java.com.ecommerce.models;

import main.java.com.ecommerce.interfaces.IProduct;

abstract class Product implements IProduct {
    protected String name;
    protected double price;
    protected int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean isAvailable() {
        return quantity > 0 && !isExpired();
    }

    @Override
    public boolean isExpired() {
        return false;
    }
}
