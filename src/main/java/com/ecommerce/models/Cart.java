package main.java.com.ecommerce.models;

import main.java.com.ecommerce.interfaces.IProduct;
import main.java.com.ecommerce.models.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();

    public void add(IProduct product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Requested quantity exceeds available stock");
        }

        // If Product already exists in cart
        Optional<CartItem> existingItem = cartItems.stream()
                .filter(item -> item.getProduct().equals(product))
                .findFirst();

        if (existingItem.isPresent()) {
            int newQuantity = existingItem.get().getQuantity() + quantity;
            if (newQuantity > product.getQuantity()) {
                throw new IllegalArgumentException("Total quantity in cart exceeds available stock");
            }
            existingItem.get().setQuantity(newQuantity);
        } else {
            cartItems.add(new CartItem(product, quantity));
        }
    }

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public double getSubtotal() {
        return cartItems.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public void clear() {
        cartItems.clear();
    }
}