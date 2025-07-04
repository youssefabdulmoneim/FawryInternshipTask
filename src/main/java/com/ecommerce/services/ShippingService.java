package main.java.com.ecommerce.services;

import main.java.com.ecommerce.interfaces.IShippingService;
import main.java.com.ecommerce.interfaces.Shippable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingService implements IShippingService {
    private static final double SHIPPING_RATE_PER_KG = 10.0;
    private static final double BASE_SHIPPING_FEE = 5.0;

    @Override
    public double calculateShippingFee(List<Shippable> items) {
        if (items.isEmpty())
            return 0.0;
        double totalWeight = items.stream().mapToDouble(Shippable::getWeight).sum();
        return BASE_SHIPPING_FEE + (totalWeight * SHIPPING_RATE_PER_KG);
    }

    @Override
    public void processShipment(List<Shippable> items) {
        if (items.isEmpty())
            return;

        System.out.println("** Shipment notice **");

        Map<String, Double> itemWeights = new HashMap<>();
        Map<String, Integer> itemCounts = new HashMap<>();

        for (Shippable item : items) {
            String name = item.getName();
            itemWeights.put(name, itemWeights.getOrDefault(name, 0.0) + item.getWeight());
            itemCounts.put(name, itemCounts.getOrDefault(name, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
            String itemName = entry.getKey();
            int count = entry.getValue();
            double weight = itemWeights.get(itemName);

            System.out.printf("%dx %s %.0fg%n", count, itemName, weight * 1000);
        }

        double totalWeight = items.stream().mapToDouble(Shippable::getWeight).sum();
        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }
}