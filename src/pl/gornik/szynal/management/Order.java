package pl.gornik.szynal.management;

import java.util.List;

public class Order {
    private static int nextId = 1;

    private final int id;
    private final List<String> parts;

    public Order(List<String> parts) {
        this.id = nextId++;
        this.parts = parts;
    }

    public int getId() {
        return id;
    }

    public List<String> getParts() {
        return parts;
    }

    public void printOrder() {
        System.out.println("Zamówienie ID: " + id);
        System.out.println("Części:");
        parts.forEach(part -> System.out.println("- " + part));
    }
}
