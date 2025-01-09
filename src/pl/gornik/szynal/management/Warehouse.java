package pl.gornik.szynal.management;

import pl.gornik.szynal.exceptions.ValidationException;

import java.util.*;

public class Warehouse {
    private final Map<String, Integer> parts = new HashMap<>();
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
        validatePart(part);
        parts.put(part, parts.getOrDefault(part, 0) + 1);
        return true;
    }

    public boolean removePart(String part) {
        validatePart(part);
        if (parts.getOrDefault(part, 0) > 0) {
            parts.put(part, parts.get(part) - 1);
            return true;
        }
        throw new ValidationException("Część " + part + " nie jest dostępna w magazynie.");
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
        System.out.println("Części w magazynie dostępne do realizacji zamówień:");
        parts.forEach((part, quantity) -> System.out.println(part + " - ilość: " + quantity));
    }

    public void listPossibleParts() {
        System.out.println("Części możliwe do zamówienia:");
        categories.forEach((category, validParts) -> {
            System.out.println("- " + category + ": " + String.join(", ", validParts));
        });
    }

    public boolean hasPart(String part) {
        return parts.getOrDefault(part, 0) > 0;
    }

    public void validatePart(String part) {
        boolean isValid = categories.values().stream().anyMatch(validParts -> validParts.contains(part));
        if (!isValid) {
            throw new ValidationException("Nieprawidłowa część: " + part);
        }
    }

    public boolean canFulfillOrder(List<String> partsToCheck) {
        for (String part : partsToCheck) {
            if (!hasPart(part)) {
                return false;
            }
        }
        return true;
    }

    public void usePart(String part) {
        if (hasPart(part)) {
            removePart(part);
            System.out.println("Zużyto część: " + part);
        } else {
            throw new ValidationException("Nie można zużyć części " + part + ", brak w magazynie.");
        }
    }

    public List<String> getPartsForOrder(List<String> requestedParts) {
        List<String> unavailableParts = new ArrayList<>();
        for (String part : requestedParts) {
            if (!hasPart(part)) {
                unavailableParts.add(part);
            }
        }
        if (!unavailableParts.isEmpty()) {
            throw new ValidationException("Brakuje części: " + String.join(", ", unavailableParts));
        }
        return requestedParts;
    }

    public Map<String, Integer> getParts() {
        return parts;
    }
}
