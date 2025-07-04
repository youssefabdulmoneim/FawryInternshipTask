package main.java.com.ecommerce.interfaces;

public interface IProduct {
    String getName();

    double getPrice();

    int getQuantity();

    void setQuantity(int quantity);

    boolean isAvailable();

    boolean isExpired();

    boolean requiresShipping();
}