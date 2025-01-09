package pl.gornik.szynal.management;

import pl.gornik.szynal.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderManager {
    private static class Order {
        private final int id; // Dodane pole ID
        private final String customerName;
        private final List<String> parts;

        public Order(int id, String customerName, List<String> parts) {
            if (customerName == null || customerName.isEmpty()) {
                throw new ValidationException("Imię klienta nie może być puste.");
            }
            if (parts == null || parts.isEmpty()) {
                throw new ValidationException("Zamówienie musi zawierać przynajmniej jedną część.");
            }
            this.id = id;
            this.customerName = customerName;
            this.parts = parts;
        }

        public int getId() {
            return id;
        }

        public String getCustomerName() {
            return customerName;
        }

        @Override
        public String toString() {
            return "ID: " + id + " | Klient: " + customerName + " | Części: " + String.join(", ", parts);
        }
    }

    private final List<Order> orders = new ArrayList<>();
    private final List<Order> fulfilledOrders = new ArrayList<>();
    private final AtomicInteger orderIdCounter = new AtomicInteger(1);

    public void addOrder(String customerName, List<String> parts) {
        orders.add(new Order(orderIdCounter.getAndIncrement(), customerName, parts));
        sortOrders();
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

    public void listFulfilledOrders() {
        System.out.println("Lista zrealizowanych zamówień:");
        if (fulfilledOrders.isEmpty()) {
            System.out.println("Brak zrealizowanych zamówień.");
        } else {
            fulfilledOrders.forEach(System.out::println);
        }
    }

    public void fulfillOrder(int orderId, Warehouse warehouse) {
        Order orderToFulfill = orders.stream()
                .filter(order -> order.getId() == orderId)
                .findFirst()
                .orElse(null);

        if (orderToFulfill != null) {

            if (warehouse.canFulfillOrder(orderToFulfill.parts)) {
                for (String part : orderToFulfill.parts) {
                    warehouse.removePart(part);
                }
                System.out.println("Zamówienie zrealizowane: " + orderToFulfill);
                fulfilledOrders.add(orderToFulfill);
                orders.remove(orderToFulfill);
            } else {
                System.out.println("Nie można zrealizować zamówienia: Brak wymaganych części w magazynie.");
            }
        } else {
            System.out.println("Nie znaleziono zamówienia o ID: " + orderId);
        }
    }

    private void sortOrders() {
        orders.sort(Comparator.comparing(Order::getCustomerName));
    }
}
