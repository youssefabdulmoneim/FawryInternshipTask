# 🛒 E-Commerce System Specification

## ✅ Features

- Define **products** with:
  - `name`
  - `price`
  - `quantity`

- Support for **perishable products** (e.g., Cheese, Biscuits) that may expire, and **non-perishable products** (e.g., TV, Mobile).

- Some products may require **shipping**, such as Cheese and TV, while others (e.g., Mobile scratch cards) do **not**.

- Every **shippable item** should provide its **weight**.

- Customers can:
  - Add a product to the cart with a **specific quantity** (not exceeding the available product quantity).
  - Checkout with items in the cart.

## 🧾 Checkout Details

On successful checkout, print to the console:

```
** Shipment notice **
2x Cheese 400g
1x Biscuits 700g
Total package weight 1.1kg

** Checkout receipt **
2x Cheese 200
1x Biscuits 150
----------------------
Subtotal 350
Shipping 30
Amount 380
Customer balance: 620
```

## ⚠️ Error Handling

Checkout should raise an error if:

- The cart is **empty**
- The customer's **balance is insufficient**
- One or more products are:
  - **Out of stock**
  - **Expired**

## 📦 Shipping Service

If applicable, collect all items that need shipping and send them to the `ShippingService`, which accepts a list of objects implementing an interface with:

```java
String getName();
double getWeight();
```

## 📝 Notes

- The solution may be implemented in any language (**Java is recommended**).
- Handle **corner cases** appropriately.
- Provide **assumptions** where necessary.
- **AI-generated solutions are not permitted.**
- Provide code examples that verify your solution works as expected.

## 📌 Example Use Case

```java
cart.add(cheese, 2);
cart.add(tv, 3);
cart.add(scratchCard, 1);
checkout(customer, cart);
```
