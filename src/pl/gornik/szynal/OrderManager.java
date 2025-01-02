package pl.gornik.szynal;

import java.util.*;

public class OrderManager {
    private final List<Map<String, String>> orders;

    public OrderManager() {
        orders = new ArrayList<>();
    }

    public void addOrder(Map<String, String> order) {
        orders.add(order);
    }

    public void listOrders() {
        if (orders.isEmpty()) {
            System.out.println("Brak zamówień.");
        } else {
            System.out.println("Lista zamówień:");
            int index = 1;
            for (Map<String, String> order : orders) {
                System.out.println("Zamówienie #" + index++);
                for (Map.Entry<String, String> entry : order.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                System.out.println("---------------");
            }
        }
    }

    public boolean fulfillOrder(int index, Warehouse warehouse) {
        if (index < 1 || index > orders.size()) {
            System.out.println("Nieprawidłowy numer zamówienia.");
            return false;
        }

        Map<String, String> order = orders.get(index - 1);
        for (String part : order.values()) {
            if (!warehouse.removePart(part)) {
                System.out.println("Brak części w magazynie: " + part);
                return false;
            }
        }

        orders.remove(index - 1);
        System.out.println("Zamówienie zostało zrealizowane.");
        return true;
    }
}
