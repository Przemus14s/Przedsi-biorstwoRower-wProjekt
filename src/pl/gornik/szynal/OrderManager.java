package pl.gornik.szynal;

import java.util.*;

public class OrderManager {
    private List<Map<String, String>> orders = new ArrayList<>();

    public void addOrder(Map<String, String> order) {
        orders.add(order);
    }

    public void listOrders() {
        if (orders.isEmpty()) {
            System.out.println("Brak zamówień.");
        } else {
            System.out.println("Zamówienia:");
            for (int i = 0; i < orders.size(); i++) {
                System.out.println((i + 1) + ". " + orders.get(i));
            }
        }
    }

    public void fulfillOrder(int index, Warehouse warehouse) {
        if (index < 1 || index > orders.size()) {
            System.out.println("Nieprawidłowy numer zamówienia.");
            return;
        }

        Map<String, String> order = orders.get(index - 1);
        boolean canFulfill = true;

        for (String part : order.values()) {
            if (!warehouse.hasPart(part)) {
                System.out.println("Brak części w magazynie: " + part);
                canFulfill = false;
            }
        }

        if (canFulfill) {
            for (String part : order.values()) {
                warehouse.removePart(part);
            }
            orders.remove(index - 1);
            System.out.println("Zamówienie zostało zrealizowane.");
        } else {
            System.out.println("Nie udało się zrealizować zamówienia.");
        }
    }
}
