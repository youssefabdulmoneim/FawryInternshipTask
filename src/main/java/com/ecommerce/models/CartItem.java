package main.java.com.ecommerce.models;

import main.java.com.ecommerce.interfaces.IProduct;

public class CartItem {
    private IProduct product;
    private int quantity;

    public CartItem(IProduct product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public IProduct getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}