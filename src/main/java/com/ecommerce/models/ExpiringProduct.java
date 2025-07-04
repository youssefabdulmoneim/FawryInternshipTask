package main.java.com.ecommerce.models;

import main.java.com.ecommerce.interfaces.Shippable;

import java.time.LocalDate;

public class ExpiringProduct extends Product implements Shippable {
    private LocalDate expiryDate;
    private double weight;

    public ExpiringProduct(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public boolean requiresShipping() {
        return true;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}