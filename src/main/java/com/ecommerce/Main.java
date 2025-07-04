package main.java.com.ecommerce;

/*
Design an e-commerce system that has these features:
● Define products with name, price and quantity.
● Some products may expire like Cheese and Biscuits while others may not expire like TV and Mobile.
● Some products may require shipping like Cheese and TV, other products like Mobile scratch cards may not require so. every shippable item should provide its weight.
● Customers should be able to add a product to cart with specific quantity not more than the available product quantity.
● Customers are able to do checkout with items in the cart.
    ○ Print in the console checkout details like
        ■ order subtotal (sum of all items’ prices)
        ■ shipping fees
        ■ paid amount (subtotal + shipping fees)
        ■ customer current balance after payment
    ○ Give an error if
        ■ Cart is empty
        ■ Customer's balance is insufficient
        ■ one product is out of stock or expired.
    ○ If applicable, collect all items that need to be shipped and send them to ShippingService which accepts a list of objects implementing an interface containing only String getName() and double getWeight() methods.
 */

import main.java.com.ecommerce.interfaces.IProduct;
import main.java.com.ecommerce.interfaces.IShippingService;
import main.java.com.ecommerce.models.*;
import main.java.com.ecommerce.services.ShippingService;
import main.java.com.ecommerce.system.ECommerceSystem;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        IShippingService shippingService = new ShippingService();
        ECommerceSystem ecommerce = new ECommerceSystem(shippingService);

        IProduct cheese = new ExpiringProduct("Cheese", 100.0, 10, LocalDate.now().plusDays(7), 0.2);
        IProduct biscuits = new ExpiringProduct("Biscuits", 150.0, 5, LocalDate.now().plusDays(30), 0.7);
        IProduct tv = new PhysicalProduct("TV", 500.0, 3, 15.0);
        IProduct mobile = new PhysicalProduct("Mobile", 800.0, 8, 0.3);
        IProduct scratchCard = new DigitalProduct("Mobile Scratch Card", 50.0, 100);

        Customer customer = new Customer("Youssef", 1000.0);

        System.out.println("\n\n=== Test Case 1: Mixed Products Checkout ===");
        Cart cart1 = new Cart();
        try {
            cart1.add(cheese, 2);
            cart1.add(biscuits, 1);
            cart1.add(scratchCard, 1);

            System.out.println("Customer balance before: " + customer.getBalance());
            ecommerce.checkout(customer, cart1);
            System.out.println("Customer balance after: " + customer.getBalance());
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


        System.out.println("\n=== Test Case 2: Shipping Required Products ===");
        Cart cart2 = new Cart();
        try {
            cart2.add(tv, 1);
            cart2.add(mobile, 2);

            System.out.println("Customer balance before: " + customer.getBalance());
            ecommerce.checkout(customer, cart2);
            System.out.println("Customer balance after: " + customer.getBalance());
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


        System.out.println("\n\n=== Test Case 3: Digital Products Only (No Shipping) ===");
        Cart cart3 = new Cart();
        try {
            cart3.add(scratchCard, 3);

            System.out.println("Customer balance before: " + customer.getBalance());
            ecommerce.checkout(customer, cart3);
            System.out.println("Customer balance after: " + customer.getBalance());
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


        System.out.println("\n=== Test Case 4: Empty Cart ===");
        Cart cart4 = new Cart();
        try {
            ecommerce.checkout(customer, cart4);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();


        System.out.println("\n=== Test Case 5: Insufficient Balance ===");
        Cart cart5 = new Cart();
        try {
            cart5.add(tv, 4);
            ecommerce.checkout(customer, cart5);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();


        System.out.println("\n=== Test Case 6: Expired Product ===");
        IProduct expiredCheese = new ExpiringProduct("Expired Cheese", 100.0, 5, LocalDate.now().minusDays(1), 0.2);
        Cart cart6 = new Cart();
        try {
            cart6.add(expiredCheese, 1);
            ecommerce.checkout(customer, cart6);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();


        System.out.println("\n=== Test Case 7: Quantity Exceeds Stock ===");
        Cart cart7 = new Cart();
        try {
            cart7.add(tv, 5);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
    }
}