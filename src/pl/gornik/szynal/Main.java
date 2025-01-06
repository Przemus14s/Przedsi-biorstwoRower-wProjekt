package pl.gornik.szynal;

import pl.gornik.szynal.management.*;
import pl.gornik.szynal.users.Authentication;
import pl.gornik.szynal.users.User;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Authentication auth = new Authentication();
        Warehouse warehouse = new Warehouse();
        CertificateManager certificateManager = new CertificateManager();
        OrderManager orderManager = new OrderManager();
        FeedbackManager feedbackManager = new FeedbackManager();
        ActivityManager activityManager = new ActivityManager();

        while (true) {
            System.out.println("=== Fabryka Rowerów ===");
            System.out.println("1. Zarejestruj się");
            System.out.println("2. Zaloguj się");
            System.out.println("3. Wyjdź");
            System.out.print("Wybierz opcję: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Podaj nazwę użytkownika: ");
                String username = scanner.nextLine();
                System.out.print("Podaj hasło: ");
                String password = scanner.nextLine();
                System.out.print("Podaj rolę (employee/manager): ");
                String role = scanner.nextLine();

                auth.register(username, password, role);
                System.out.println("Rejestracja zakończona sukcesem!");

            } else if (choice == 2) {
                System.out.print("Nazwa użytkownika: ");
                String username = scanner.nextLine();
                System.out.print("Hasło: ");
                String password = scanner.nextLine();

                User loggedInUser = auth.login(username, password);

                if (loggedInUser != null) {
                    System.out.println("Zalogowano pomyślnie! Witaj, " + loggedInUser.getRole() + " " + loggedInUser.getUsername() + ".");

                    if ("manager".equals(loggedInUser.getRole())) {
                        while (true) {
                            System.out.println("\n=== Menu Kierownika ===");
                            System.out.println("1. Zarządzaj magazynem");
                            System.out.println("2. Zarządzaj certyfikatami");
                            System.out.println("3. Wyświetl zamówienia");
                            System.out.println("4. Realizuj zamówienie");
                            System.out.println("5. Dodaj aktywność");
                            System.out.println("6. Wyświetl aktywności");
                            System.out.println("7. Wyloguj się");
                            System.out.print("Wybierz opcję: ");

                            int managerChoice = scanner.nextInt();
                            scanner.nextLine();

                            if (managerChoice == 1) {
                                System.out.println("1. Dodaj część");
                                System.out.println("2. Wyświetl części");
                                System.out.print("Wybierz opcję: ");
                                int warehouseChoice = scanner.nextInt();
                                scanner.nextLine();

                                if (warehouseChoice == 1) {
                                    System.out.print("Podaj nazwę części do dodania: ");
                                    String part = scanner.nextLine();
                                    warehouse.addPart(part);
                                    System.out.println("Dodano część do magazynu.");
                                } else if (warehouseChoice == 2) {
                                    warehouse.listParts();
                                }

                            } else if (managerChoice == 2) {
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

                            } else if (managerChoice == 3) {
                                orderManager.listOrders();

                            } else if (managerChoice == 4) {
                                System.out.print("Podaj numer zamówienia do realizacji: ");
                                int orderIndex = scanner.nextInt();
                                scanner.nextLine();
                                orderManager.fulfillOrder(orderIndex, warehouse);

                            } else if (managerChoice == 5) {
                                System.out.print("Podaj opis aktywności: ");
                                String activity = scanner.nextLine();
                                activityManager.addActivity(activity);
                                System.out.println("Dodano aktywność.");

                            } else if (managerChoice == 6) {
                                activityManager.listActivities();

                            } else if (managerChoice == 7) {
                                System.out.println("Wylogowano.");
                                break;
                            } else {
                                System.out.println("Nieprawidłowa opcja.");
                            }
                        }

                    } else if ("employee".equals(loggedInUser.getRole())) {
                        while (true) {
                            System.out.println("\n=== Menu Pracownika ===");
                            System.out.println("1. Złóż zamówienie");
                            System.out.println("2. Wyświetl certyfikaty");
                            System.out.println("3. Wyświetl opinie");
                            System.out.println("4. Dodaj opinię");
                            System.out.println("5. Wyloguj się");
                            System.out.print("Wybierz opcję: ");

                            int employeeChoice = scanner.nextInt();
                            scanner.nextLine();

                            if (employeeChoice == 1) {
                                Map<String, String> order = new HashMap<>();
                                System.out.print("Podaj typ ramy: ");
                                order.put("Rama", scanner.nextLine());
                                System.out.print("Podaj rozmiar kół: ");
                                order.put("Koła", scanner.nextLine());
                                System.out.print("Podaj typ pedałów: ");
                                order.put("Pedały", scanner.nextLine());
                                System.out.print("Podaj typ kierownicy: ");
                                order.put("Kierownica", scanner.nextLine());
                                System.out.print("Podaj typ dzwonka: ");
                                order.put("Dzwonek", scanner.nextLine());
                                System.out.print("Podaj typ świateł: ");
                                order.put("Światła", scanner.nextLine());

                                orderManager.addOrder(order);
                                System.out.println("Złożono zamówienie.");

                            } else if (employeeChoice == 2) {
                                certificateManager.listCertificates();

                            } else if (employeeChoice == 3) {
                                feedbackManager.listFeedbacks();

                            } else if (employeeChoice == 4) {
                                System.out.print("Podaj ocenę (1-5): ");
                                int rating = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Podaj komentarz: ");
                                String comment = scanner.nextLine();
                                feedbackManager.addFeedback(rating, comment);
                                System.out.println("Dodano opinię.");

                            } else if (employeeChoice == 5) {
                                System.out.println("Wylogowano.");
                                break;
                            } else {
                                System.out.println("Nieprawidłowa opcja.");
                            }
                        }
                    }
                } else {
                    System.out.println("Nieprawidłowa nazwa użytkownika lub hasło.");
                }

            } else if (choice == 3) {
                System.out.println("Do widzenia!");
                break;
            } else {
                System.out.println("Nieprawidłowa opcja.");
            }
        }

        scanner.close();
    }
}
