package pl.gornik.szynal.management;

import java.util.*;

public class SortedWarehouse extends Warehouse {

    @Override
    public void listAvailableParts() {
        System.out.println("Części w magazynie dostępne do realizacji zamówień:");

        List<String> sortedParts = new ArrayList<>(super.getParts().keySet());
        Collections.sort(sortedParts);
        for (String part : sortedParts) {
            System.out.println(part + " - ilość: " + super.getParts().get(part));
        }
    }
}
