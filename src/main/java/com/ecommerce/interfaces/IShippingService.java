package main.java.com.ecommerce.interfaces;

import java.util.List;

public interface IShippingService {
    double calculateShippingFee(List<Shippable> items);

    void processShipment(List<Shippable> items);
}