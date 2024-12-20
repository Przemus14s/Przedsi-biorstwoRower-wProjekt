package pl.gornik.szynal;

import java.util.*;

public class Warehouse {
    private List<String> parts;

    public Warehouse() {
        parts = new ArrayList<>();
    }

    public void addPart(String part) {
        parts.add(part);
    }

    public void listParts() {
        if (parts.isEmpty()) {
            System.out.println("The warehouse is empty.");
        } else {
            System.out.println("Parts in the warehouse:");
            for (String part : parts) {
                System.out.println("- " + part);
            }
        }
    }
}