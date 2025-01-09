package pl.gornik.szynal.management;

import pl.gornik.szynal.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderManager {
    private static class Order {
        private final String customerName;
        private final List<String> parts;

        public Order(String customerName, List<String> parts) {
            if (customerName == null || customerName.isEmpty()) {
                throw new ValidationException("Imię klienta nie może być puste.");
            }
            if (parts == null || parts.isEmpty()) {
                throw new ValidationException("Zamówienie musi zawierać przynajmniej jedną część.");
            }
            this.customerName = customerName;
            this.parts = parts;
        }

        @Override
        public String toString() {
            return "Zamówienie od " + customerName + ": " + String.join(", ", parts);
        }
    }

    private final List<Order> orders = new ArrayList<>();

    public void addOrder(String customerName, List<String> parts) {
        orders.add(new Order(customerName, parts));
    }

    public void addOrder(Map<String, String> orderDetails) {
        List<String> parts = new ArrayList<>(orderDetails.values());
        String customerName = orderDetails.keySet().iterator().next();
        addOrder(customerName, parts);
    }

    public void listOrders() {
        System.out.println("Lista zamówień:");
        if (orders.isEmpty()) {
            System.out.println("Brak zamówień.");
        } else {
            orders.forEach(System.out::println);
        }
    }

    public void fulfillOrder(int orderIndex, Warehouse warehouse) {
        if (orderIndex >= 0 && orderIndex < orders.size()) {
            Order order = orders.get(orderIndex);
            System.out.println("Realizowanie zamówienia: " + order);

        } else {
            System.out.println("Nieprawidłowy numer zamówienia.");
        }
    }
}
