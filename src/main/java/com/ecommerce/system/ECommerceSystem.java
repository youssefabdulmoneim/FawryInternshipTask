package main.java.com.ecommerce.system;

import main.java.com.ecommerce.exceptions.OrderProcessingException;
import main.java.com.ecommerce.interfaces.IProduct;
import main.java.com.ecommerce.interfaces.IShippingService;
import main.java.com.ecommerce.interfaces.Shippable;
import main.java.com.ecommerce.models.Cart;
import main.java.com.ecommerce.models.CartItem;
import main.java.com.ecommerce.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class ECommerceSystem {
    private IShippingService shippingService;

    public ECommerceSystem(IShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public void checkout(Customer customer, Cart cart) throws OrderProcessingException {
        validateCheckout(customer, cart);

        double subtotal = cart.getSubtotal();
        List<Shippable> shippableItems = getShippableItems(cart);
        double shippingFee = shippingService.calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFee;

        customer.deductBalance(totalAmount);

        updateProductQuantities(cart);

        if (!shippableItems.isEmpty()) {
            shippingService.processShipment(shippableItems);
        }

        printCheckoutReceipt(cart, subtotal, shippingFee, totalAmount);

        cart.clear();
    }

    private void validateCheckout(Customer customer, Cart cart) throws OrderProcessingException {
        if (cart.isEmpty()) {
            throw new OrderProcessingException("Cart is empty");
        }

        for (CartItem item : cart.getCartItems()) {
            IProduct product = item.getProduct();
            if (!product.isAvailable()) {
                throw new OrderProcessingException("Product " + product.getName() + " is out of stock or expired");
            }
            if (item.getQuantity() > product.getQuantity()) {
                throw new OrderProcessingException("Product " + product.getName() + " has insufficient stock");
            }
        }

        double subtotal = cart.getSubtotal();
        List<Shippable> shippableItems = getShippableItems(cart);
        double shippingFee = shippingService.calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFee;

        if (customer.getBalance() < totalAmount) {
            throw new OrderProcessingException("Customer has insufficient balance");
        }
    }

    private List<Shippable> getShippableItems(Cart cart) {
        List<Shippable> shippableItems = new ArrayList<>();
        for (CartItem item : cart.getCartItems()) {
            IProduct product = item.getProduct();
            if (product.requiresShipping() && product instanceof Shippable) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add((Shippable) product);
                }
            }
        }
        return shippableItems;
    }

    private void updateProductQuantities(Cart cart) {
        for (CartItem item : cart.getCartItems()) {
            IProduct product = item.getProduct();
            int newQuantity = product.getQuantity() - item.getQuantity();
            product.setQuantity(newQuantity);
        }
    }

    private void printCheckoutReceipt(Cart cart, double subtotal, double shippingFee, double totalAmount) {
        System.out.println("** Checkout receipt **");

        for (CartItem item : cart.getCartItems()) {
            IProduct product = item.getProduct();
            System.out.printf("%dx %s %.0f%n",
                    item.getQuantity(),
                    product.getName(),
                    item.getTotalPrice());
        }

        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f%n", subtotal);

        if (shippingFee > 0) {
            System.out.printf("Shipping %.0f%n", shippingFee);
        }

        System.out.printf("Amount %.0f%n", totalAmount);
    }
}