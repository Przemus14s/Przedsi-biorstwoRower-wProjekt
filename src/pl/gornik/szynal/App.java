package pl.gornik.szynal;

import pl.gornik.szynal.management.*;
import pl.gornik.szynal.users.*;

import java.util.*;

public class App {
    private final Scanner scanner = new Scanner(System.in);
    private final Authentication auth = new Authentication();
    private final Warehouse warehouse = new Warehouse();
    private final CertificateManager certificateManager = new CertificateManager();
    private final OrderManager orderManager = new OrderManager();
    private final FeedbackManager feedbackManager = new FeedbackManager();
    private final ActivityManager activityManager = new ActivityManager();

    public void run() {
        while (true) {
            System.out.println("=== Fabryka Rowerów ===");
            System.out.println("1. Zarejestruj się");
            System.out.println("2. Zaloguj się");
            System.out.println("3. Wyjdź");
            System.out.print("Wybierz opcję: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> {
                    System.out.println("Do widzenia!");
                    return;
                }
                default -> System.out.println("Nieprawidłowa opcja.");
            }
        }
    }

    private void register() {
        System.out.print("Podaj nazwę użytkownika: ");
        String username = scanner.nextLine();
        System.out.print("Podaj hasło: ");
        String password = scanner.nextLine();
        System.out.print("Podaj rolę (Menadżer, Pracownik, Klient): ");
        String roleInput = scanner.nextLine();

        try {
            Role role = auth.getRoleFromString(roleInput);
            auth.register(username, password, role);
            System.out.println("Rejestracja zakończona sukcesem!");
        } catch (IllegalArgumentException e) {
            System.out.println("Nieprawidłowa rola. Rejestracja nie powiodła się.");
        }
    }

    private void login() {
        System.out.print("Podaj nazwę użytkownika: ");
        String username = scanner.nextLine();
        System.out.print("Podaj hasło: ");
        String password = scanner.nextLine();

        try {
            User loggedInUser = auth.login(username, password);
            System.out.println("Zalogowano pomyślnie! Witaj, " + loggedInUser.getRole().getDisplayName() + " " + loggedInUser.getUsername() + ".");
            switch (loggedInUser.getRole()) {
                case MANAGER -> managerMenu();
                case EMPLOYEE -> employeeMenu();
                case CLIENT -> clientMenu();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void managerMenu() {
        while (true) {
            System.out.println("\n=== Menu Menadżera ===");
            System.out.println("1. Zarządzanie certyfikatami");
            System.out.println("2. Dodanie aktywności");
            System.out.println("3. Wyświetlenie aktywności");
            System.out.println("4. Zarządzanie magazynem");
            System.out.println("5. Wyloguj się");
            System.out.print("Wybierz opcję: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> certificateMenu();
                case 2 -> addActivity();
                case 3 -> activityManager.listActivities();
                case 4 -> warehouseMenu();
                case 5 -> {
                    System.out.println("Wylogowano.");
                    return;
                }
                default -> System.out.println("Nieprawidłowa opcja.");
            }
        }
    }

    private void employeeMenu() {
        while (true) {
            System.out.println("\n=== Menu Pracownika ===");
            System.out.println("1. Zarządzanie magazynem");
            System.out.println("2. Wyświetl zamówienia");
            System.out.println("3. Realizuj zamówienie");
            System.out.println("4. Wyloguj się");
            System.out.print("Wybierz opcję: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> warehouseMenu();
                case 2 -> orderManager.listOrders();
                case 3 -> fulfillOrder();
                case 4 -> {
                    System.out.println("Wylogowano.");
                    return;
                }
                default -> System.out.println("Nieprawidłowa opcja.");
            }
        }
    }

    private void clientMenu() {
        while (true) {
            System.out.println("\n=== Menu Klienta ===");
            System.out.println("1. Złóż zamówienie");
            System.out.println("2. Wyświetl certyfikaty");
            System.out.println("3. Wyświetl opinie");
            System.out.println("4. Dodaj opinię");
            System.out.println("5. Wyloguj się");
            System.out.print("Wybierz opcję: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> placeOrder();
                case 2 -> certificateManager.listCertificates();
                case 3 -> feedbackManager.listFeedbacks();
                case 4 -> addFeedback();
                case 5 -> {
                    System.out.println("Wylogowano.");
                    return;
                }
                default -> System.out.println("Nieprawidłowa opcja.");
            }
        }
    }

    private void warehouseMenu() {
        while (true) {
            System.out.println("\n=== Zarządzanie Magazynem ===");
            System.out.println("1. Dodaj część");
            System.out.println("2. Wyświetl części w magazynie");
            System.out.println("3. Wyświetl dostępne części");
            System.out.println("4. Powrót");
            System.out.print("Wybierz opcję: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Podaj nazwę części do dodania: ");
                    String part = scanner.nextLine();
                    if (warehouse.addPart(part)) {
                        System.out.println("Dodano część do magazynu.");
                    } else {
                        System.out.println("Nieprawidłowa część.");
                    }
                }
                case 2 -> warehouse.listParts();
                case 3 -> warehouse.listAvailableParts();
                case 4 -> {
                    return;
                }
                default -> System.out.println("Nieprawidłowa opcja.");
            }
        }
    }

    private void certificateMenu() {
        System.out.println("1. Dodaj certyfikat");
        System.out.println("2. Wyświetl certyfikaty");
        System.out.print("Wybierz opcję: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.print("Podaj nazwę certyfikatu: ");
                String certificate = scanner.nextLine();
                System.out.print("Podaj treść certyfikatu: ");
                String certificateContent = scanner.nextLine();
                certificateManager.addCertificate(certificate, certificateContent);
                System.out.println("Dodano certyfikat.");
            }
            case 2 -> certificateManager.listCertificates();
            default -> System.out.println("Nieprawidłowa opcja.");
        }
    }

    private void fulfillOrder() {
        System.out.print("Podaj numer zamówienia do realizacji: ");
        int orderIndex = scanner.nextInt();
        scanner.nextLine();
        orderManager.fulfillOrder(orderIndex, warehouse);
    }

    private void addActivity() {
        System.out.print("Podaj opis aktywności: ");
        String activity = scanner.nextLine();
        activityManager.addActivity(activity);
        System.out.println("Dodano aktywność.");
    }

    private void placeOrder() {
        Map<String, String> order = new HashMap<>();
        System.out.println("=== Składanie zamówienia ===");
        System.out.println("Dostępne części:");
        warehouse.listAvailableParts();

        for (String category : Warehouse.categories.keySet()) {
            System.out.print("Wybierz " + category + ": ");
            String part = scanner.nextLine();
            if (Warehouse.categories.get(category).contains(part)) {
                order.put(category, part);
            } else {
                System.out.println("Nieprawidłowa nazwa części. Zamówienie przerwane.");
                return;
            }
        }

        orderManager.addOrder(order);
        System.out.println("Złożono zamówienie.");
    }

    private void addFeedback() {
        System.out.print("Podaj ocenę (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Podaj komentarz: ");
        String comment = scanner.nextLine();

        if (rating < 1 || rating > 5) {
            System.out.println("Nieprawidłowa ocena. Musi być w zakresie 1-5.");
            return;
        }

        feedbackManager.addFeedback(rating, comment);
        System.out.println("Dodano opinię.");
    }
}
