package pl.gornik.szynal.management;

import java.util.ArrayList;
import java.util.List;

public class ActivityManager {
    private List<String> activities = new ArrayList<>();

    public void addActivity(String activity) {
        activities.add(activity);
        System.out.println("Dodano aktywność: " + activity);
    }

    public void listActivities() {
        if (activities.isEmpty()) {
            System.out.println("Brak zapisanych aktywności.");
        } else {
            System.out.println("Lista aktywności:");
            for (String activity : activities) {
                System.out.println("- " + activity);
            }
        }
    }
}