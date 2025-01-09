package pl.gornik.szynal.management;

import java.util.*;

public class Warehouse {
    private Map<String, Integer> parts = new HashMap<>();
    public static final Map<String, List<String>> categories = new HashMap<>();

    static {
        categories.put("Rama", Arrays.asList("Rama aluminiowa", "Rama karbonowa", "Rama stalowa"));
        categories.put("Koła", Arrays.asList("Koła szosowe", "Koła terenowe", "Koła miejskie"));
        categories.put("Pedały", Arrays.asList("Pedały platformowe", "Pedały zatrzaskowe", "Pedały składane"));
        categories.put("Kierownica", Arrays.asList("Kierownica prosta", "Kierownica gięta", "Kierownica typu baranek"));
        categories.put("Dzwonek", Arrays.asList("Dzwonek klasyczny", "Dzwonek elektroniczny", "Dzwonek retro"));
        categories.put("Światła", Arrays.asList("Światło LED przednie", "Światło tylne LED", "Światło halogenowe"));
    }

    public boolean addPart(String part) {
        for (List<String> validParts : categories.values()) {
            if (validParts.contains(part)) {
                parts.put(part, parts.getOrDefault(part, 0) + 1);
                return true;
            }
        }
        return false;
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
        if (parts.isEmpty()) {
            System.out.println("Brak części w magazynie.");
        } else {
            parts.forEach((part, quantity) -> System.out.println(part + ": " + quantity));
        }
    }

    public void listAvailableParts() {
        System.out.println("Dostępne części do magazynowania:");
        categories.forEach((category, validParts) -> {
            System.out.println("- " + category + ": " + String.join(", ", validParts));
        });
    }

    public boolean hasPart(String part) {
        return parts.getOrDefault(part, 0) > 0;
    }
}
