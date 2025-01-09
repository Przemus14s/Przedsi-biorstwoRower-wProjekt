package pl.gornik.szynal;

import pl.gornik.szynal.management.*;
import pl.gornik.szynal.users.*;

import java.util.*;

public class App {
    private final Scanner scanner = new Scanner(System.in);
    private final Authentication auth = new Authentication();
    private final Warehouse warehouse = new SortedWarehouse();
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
                case CLIENT -> clientMenu(username);
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
            System.out.println("5. Wyświetl zrealizowane zamówienia");
            System.out.println("6. Zmień hasło");
            System.out.println("7. Wyloguj się");
            System.out.print("Wybierz opcję: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> certificateMenu();
                case 2 -> addActivity();
                case 3 -> activityManager.listActivities();
                case 4 -> warehouseMenu();
                case 5 -> orderManager.listFulfilledOrders();
                case 6 -> changePassword(); // Nowa opcja zmiany hasła
                case 7 -> {
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
            System.out.println("4. Wyświetl zrealizowane zamówienia");
            System.out.println("5. Zmień hasło");
            System.out.println("6. Wyloguj się");
            System.out.print("Wybierz opcję: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> warehouseMenu();
                case 2 -> orderManager.listOrders();
                case 3 -> fulfillOrder();
                case 4 -> orderManager.listFulfilledOrders();
                case 5 -> changePassword();
                case 6 -> {
                    System.out.println("Wylogowano.");
                    return;
                }
                default -> System.out.println("Nieprawidłowa opcja.");
            }
        }
    }

    private void clientMenu(String username) {
        while (true) {
            System.out.println("\n=== Menu Klienta ===");
            System.out.println("1. Złóż zamówienie");
            System.out.println("2. Wyświetl certyfikaty");
            System.out.println("3. Wyświetl opinie");
            System.out.println("4. Dodaj opinię");
            System.out.println("5. Zmień hasło");
            System.out.println("6. Wyloguj się");
            System.out.print("Wybierz opcję: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> placeOrder(username);
                case 2 -> certificateManager.listCertificates();
                case 3 -> feedbackManager.listFeedbacks();
                case 4 -> addFeedback(username);
                case 5 -> changePassword();
                case 6 -> {
                    System.out.println("Wylogowano.");
                    return;
                }
                default -> System.out.println("Nieprawidłowa opcja.");
            }
        }
    }

    private void changePassword() {
        System.out.print("Podaj stare hasło: ");
        String oldPassword = scanner.nextLine();
        System.out.print("Podaj nowe hasło: ");
        String newPassword = scanner.nextLine();
        System.out.print("Podaj nazwę użytkownika: ");
        String username = scanner.nextLine();

        try {
            auth.changePassword(username, oldPassword, newPassword);
            System.out.println("Hasło zostało zmienione pomyślnie.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void placeOrder(String username) {
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

        orderManager.addOrder(username, new ArrayList<>(order.values()));
        System.out.println("Złożono zamówienie.");
    }

    private void fulfillOrder() {
        orderManager.listOrders();
        System.out.print("Podaj numer zamówienia do realizacji: ");
        int orderIndex = scanner.nextInt();
        scanner.nextLine();
        orderManager.fulfillOrder(orderIndex, warehouse);
    }

    private void warehouseMenu() {
        while (true) {
            System.out.println("\n=== Menu Magazynu ===");
            System.out.println("1. Wyświetl dostępne części");
            System.out.println("2. Dodaj nową część");
            System.out.println("3. Usuń część");
            System.out.println("4. Powrót do menu");
            System.out.print("Wybierz opcję: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> warehouse.listAvailableParts();
                case 2 -> addPart();
                case 3 -> removePart();
                case 4 -> {
                    return;
                }
                default -> System.out.println("Nieprawidłowa opcja.");
            }
        }
    }

    private void certificateMenu() {
        while (true) {
            System.out.println("\n=== Menu Certyfikatów ===");
            System.out.println("1. Wyświetl certyfikaty");
            System.out.println("2. Dodaj nowy certyfikat");
            System.out.println("3. Powrót do menu");
            System.out.print("Wybierz opcję: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> certificateManager.listCertificates();
                case 2 -> addCertificate();
                case 3 -> {
                    return;
                }
                default -> System.out.println("Nieprawidłowa opcja.");
            }
        }
    }

    private void addActivity() {
        System.out.print("Podaj nazwę aktywności: ");
        String activityName = scanner.nextLine();
        activityManager.addActivity(activityName);
        System.out.println("Aktywność została dodana.");
    }

    private void addFeedback(String username) {
        System.out.print("Podaj swoją opinię: ");
        String feedbackText = scanner.nextLine();
        feedbackManager.addFeedback(Integer.parseInt(username), feedbackText);
        System.out.println("Opinia została dodana.");
    }

    private void addCertificate() {
        System.out.print("Podaj nazwę certyfikatu: ");
        String certificateName = scanner.nextLine();
        System.out.print("Podaj opis certyfikatu: ");
        String certificateDescription = scanner.nextLine();
        certificateManager.addCertificate(certificateName, certificateDescription);
        System.out.println("Certyfikat został dodany.");
    }

    private void addPart() {
        System.out.print("Podaj nazwę nowej części: ");
        String partName = scanner.nextLine();
        warehouse.addPart(partName);
        System.out.println("Nowa część została dodana do magazynu.");
    }

    private void removePart() {
        System.out.print("Podaj nazwę części do usunięcia: ");
        String partName = scanner.nextLine();
        warehouse.removePart(partName);
        System.out.println("Część została usunięta z magazynu.");
    }
}