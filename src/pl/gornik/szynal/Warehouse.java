package pl.gornik.szynal;

import java.util.*;

public class Warehouse {
    private final List<String> parts;

    public Warehouse() {
        parts = new ArrayList<>();
    }

    public void addPart(String part) {
        parts.add(part);
    }

    public boolean removePart(String part) {
        if (parts.contains(part)) {
            parts.remove(part);
            return true;
        }
        return false;
    }

    public void listParts() {
        if (parts.isEmpty()) {
            System.out.println("Magazyn jest pusty.");
        } else {
            System.out.println("Części w magazynie:");
            for (String part : parts) {
                System.out.println("- " + part);
            }
        }
    }
}