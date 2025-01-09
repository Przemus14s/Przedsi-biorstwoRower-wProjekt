package pl.gornik.szynal.management;

import pl.gornik.szynal.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class ActivityManager {
    private final List<String> activities = new ArrayList<>();

    public void addActivity(String activity) {
        if (activity == null || activity.isEmpty()) {
            throw new ValidationException("Aktywność nie może być pusta.");
        }
        activities.add(activity);
    }

    public void listActivities() {
        System.out.println("Lista aktywności:");
        if (activities.isEmpty()) {
            System.out.println("Brak zarejestrowanych aktywności.");
        } else {
            activities.forEach(System.out::println);
        }
    }
}
