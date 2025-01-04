package pl.gornik.szynal;

import java.util.*;

public class Warehouse {
    private Map<String, Integer> parts = new HashMap<>();

    public void addPart(String part) {
        parts.put(part, parts.getOrDefault(part, 0) + 1);
    }

    public boolean removePart(String part) {
        if (parts.getOrDefault(part, 0) > 0) {
            parts.put(part, parts.get(part) - 1);
            return true;
        }
        return false;
    }

    public void listParts() {
        System.out.println("Części w magazynie:");
        parts.forEach((part, quantity) -> System.out.println(part + ": " + quantity));
    }

    public boolean hasPart(String part) {
        return parts.getOrDefault(part, 0) > 0;
    }
}