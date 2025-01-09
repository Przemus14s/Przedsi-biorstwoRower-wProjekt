package pl.gornik.szynal;

import pl.gornik.szynal.management.*;
import pl.gornik.szynal.users.Authentication;
import pl.gornik.szynal.users.User;

import java.util.*;

public class App {
    private Scanner scanner = new Scanner(System.in);
    private Authentication auth = new Authentication();
    private Warehouse warehouse = new Warehouse();
    private CertificateManager certificateManager = new CertificateManager();
    private OrderManager orderManager = new OrderManager();
    private FeedbackManager feedbackManager = new FeedbackManager();
    private ActivityManager activityManager = new ActivityManager();

    public void run() {
        while (true) {
            System.out.println("=== Fabryka Rowerów ===");
            System.out.println("1. Zarejestruj się");
            System.out.println("2. Zaloguj się");
            System.out.println("3. Wyjdź");
            System.out.print("Wybierz opcję: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                register();
            } else if (choice == 2) {
                login();
            } else if (choice == 3) {
                System.out.println("Do widzenia!");
                break;
            } else {
                System.out.println("Nieprawidłowa opcja.");
            }
        }

        scanner.close();
    }

    private void register() {
        System.out.print("Podaj nazwę użytkownika: ");
        String username = scanner.nextLine();
        System.out.print("Podaj hasło: ");
        String password = scanner.nextLine();
        System.out.print("Podaj rolę (menadżer/pracownik/klient): ");
        String role = scanner.nextLine();

        auth.register(username, password, role);
        System.out.println("Rejestracja zakończona sukcesem!");
    }

    private void login() {
        System.out.print("Nazwa użytkownika: ");
        String username = scanner.nextLine();
        System.out.print("Hasło: ");
        String password = scanner.nextLine();

        User loggedInUser = auth.login(username, password);

        if (loggedInUser != null) {
            System.out.println("Zalogowano pomyślnie! Witaj, " + loggedInUser.getRole() + " " + loggedInUser.getUsername() + ".");
            if ("menadżer".equals(loggedInUser.getRole())) {
                managerMenu();
            } else if ("pracownik".equals(loggedInUser.getRole())) {
                employeeMenu();
            } else if ("klient".equals(loggedInUser.getRole())) {
                clientMenu();
            }
        } else {
            System.out.println("Nieprawidłowa nazwa użytkownika lub hasło.");
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

            int managerChoice = scanner.nextInt();
            scanner.nextLine();

            if (managerChoice == 1) {
                certificateMenu();
            } else if (managerChoice == 2) {
                addActivity();
            } else if (managerChoice == 3) {
                activityManager.listActivities();
            } else if (managerChoice == 4) {
                warehouseMenu();
            } else if (managerChoice == 5) {
                System.out.println("Wylogowano.");
                break;
            } else {
                System.out.println("Nieprawidłowa opcja.");
            }
        }
    }

    private void employeeMenu() {
        while (true) {
            System.out.println("\n=== Menu Pracownika ===");
            System.out.println("1. Zarządzaj magazynem");
            System.out.println("2. Wyświetl zamówienia");
            System.out.println("3. Realizuj zamówienie");
            System.out.println("4. Wyloguj się");
            System.out.print("Wybierz opcję: ");

            int employeeChoice = scanner.nextInt();
            scanner.nextLine();

            if (employeeChoice == 1) {
                warehouseMenu();
            } else if (employeeChoice == 2) {
                orderManager.listOrders();
            } else if (employeeChoice == 3) {
                fulfillOrder();
            } else if (employeeChoice == 4) {
                System.out.println("Wylogowano.");
                break;
            } else {
                System.out.println("Nieprawidłowa opcja.");
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

            int clientChoice = scanner.nextInt();
            scanner.nextLine();

            if (clientChoice == 1) {
                placeOrder();
            } else if (clientChoice == 2) {
                certificateManager.listCertificates();
            } else if (clientChoice == 3) {
                feedbackManager.listFeedbacks();
            } else if (clientChoice == 4) {
                addFeedback();
            } else if (clientChoice == 5) {
                System.out.println("Wylogowano.");
                break;
            } else {
                System.out.println("Nieprawidłowa opcja.");
            }
        }
    }

    private void warehouseMenu() {
        while (true) {
            System.out.println("\n=== Zarządzanie magazynem ===");
            System.out.println("1. Dodaj część");
            System.out.println("2. Wyświetl części w magazynie");
            System.out.println("3. Wyświetl dostępne części");
            System.out.println("4. Powrót");
            System.out.print("Wybierz opcję: ");
            int warehouseChoice = scanner.nextInt();
            scanner.nextLine();

            if (warehouseChoice == 1) {
                System.out.print("Podaj nazwę części do dodania: ");
                String part = scanner.nextLine();
                if (warehouse.addPart(part)) {
                    System.out.println("Dodano część do magazynu.");
                } else {
                    System.out.println("Nieprawidłowa część.");
                }
            } else if (warehouseChoice == 2) {
                warehouse.listParts();
            } else if (warehouseChoice == 3) {
                warehouse.listAvailableParts();
            } else if (warehouseChoice == 4) {
                break;
            } else {
                System.out.println("Nieprawidłowa opcja.");
            }
        }
    }

    private void certificateMenu() {
        System.out.println("1. Dodaj certyfikat");
        System.out.println("2. Wyświetl certyfikaty");
        System.out.print("Wybierz opcję: ");
        int certChoice = scanner.nextInt();
        scanner.nextLine();

        if (certChoice == 1) {
            System.out.print("Podaj treść certyfikatu: ");
            String certificate = scanner.nextLine();
            certificateManager.addCertificate(certificate);
            System.out.println("Dodano certyfikat.");
        } else if (certChoice == 2) {
            certificateManager.listCertificates();
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
        feedbackManager.addFeedback(rating, comment);
        System.out.println("Dodano opinię.");
    }
}
