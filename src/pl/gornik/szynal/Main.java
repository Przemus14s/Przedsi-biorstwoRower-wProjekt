package pl.gornik.szynal;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Authentication auth = new Authentication();
        Warehouse warehouse = new Warehouse();
        CertificateManager certificateManager = new CertificateManager();
        OrderManager orderManager = new OrderManager();

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
                            System.out.println("\nMenu Kierownika:");
                            System.out.println("1. Zarządzaj certyfikatami");
                            System.out.println("2. Zarządzaj magazynem");
                            System.out.println("3. Zarządzaj zamówieniami");
                            System.out.println("4. Wyloguj się");
                            System.out.print("Wybierz opcję: ");

                            int managerChoice = scanner.nextInt();
                            scanner.nextLine();

                            if (managerChoice == 1) {
                                while (true) {
                                    System.out.println("\nMenu Certyfikatów:");
                                    System.out.println("1. Dodaj certyfikat");
                                    System.out.println("2. Wyświetl certyfikaty");
                                    System.out.println("3. Wróć");
                                    System.out.print("Wybierz opcję: ");

                                    int certChoice = scanner.nextInt();
                                    scanner.nextLine();

                                    if (certChoice == 1) {
                                        System.out.print("Podaj nazwę certyfikatu: ");
                                        String certificate = scanner.nextLine();
                                        certificateManager.addCertificate(certificate);
                                        System.out.println("Certyfikat został dodany.");
                                    } else if (certChoice == 2) {
                                        certificateManager.listCertificates();
                                    } else if (certChoice == 3) {
                                        break;
                                    } else {
                                        System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.");
                                    }
                                }
                            } else if (managerChoice == 2) {
                                while (true) {
                                    System.out.println("\nMenu Magazynu:");
                                    System.out.println("1. Dodaj część");
                                    System.out.println("2. Wyświetl części");
                                    System.out.println("3. Wróć");
                                    System.out.print("Wybierz opcję: ");

                                    int whChoice = scanner.nextInt();
                                    scanner.nextLine();

                                    if (whChoice == 1) {
                                        System.out.print("Podaj nazwę części do dodania: ");
                                        String part = scanner.nextLine();
                                        warehouse.addPart(part);
                                        System.out.println("Część została dodana.");
                                    } else if (whChoice == 2) {
                                        warehouse.listParts();
                                    } else if (whChoice == 3) {
                                        break;
                                    } else {
                                        System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.");
                                    }
                                }
                            } else if (managerChoice == 3) {
                                while (true) {
                                    System.out.println("\nMenu Zamówień:");
                                    System.out.println("1. Wyświetl zamówienia");
                                    System.out.println("2. Zrealizuj zamówienie");
                                    System.out.println("3. Wróć");
                                    System.out.print("Wybierz opcję: ");

                                    int orderChoice = scanner.nextInt();
                                    scanner.nextLine();

                                    if (orderChoice == 1) {
                                        orderManager.listOrders();
                                    } else if (orderChoice == 2) {
                                        System.out.print("Podaj numer zamówienia do realizacji: ");
                                        int orderIndex = scanner.nextInt();
                                        scanner.nextLine();
                                        orderManager.fulfillOrder(orderIndex, warehouse);
                                    } else if (orderChoice == 3) {
                                        break;
                                    } else {
                                        System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.");
                                    }
                                }
                            } else if (managerChoice == 4) {
                                System.out.println("Wylogowano.");
                                break;
                            } else {
                                System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.");
                            }
                        }
                    } else if ("employee".equals(loggedInUser.getRole())) {
                        while (true) {
                            System.out.println("\nMenu Pracownika:");
                            System.out.println("1. Złóż zamówienie");
                            System.out.println("2. Wyświetl certyfikaty");
                            System.out.println("3. Wyloguj się");
                            System.out.print("Wybierz opcję: ");

                            int employeeChoice = scanner.nextInt();
                            scanner.nextLine();

                            if (employeeChoice == 1) {
                                Map<String, String> order = new LinkedHashMap<>();
                                System.out.print("Podaj ramę: ");
                                order.put("Rama", scanner.nextLine());
                                System.out.print("Podaj rozmiar kół: ");
                                order.put("Rozmiar kół", scanner.nextLine());
                                System.out.print("Podaj pedały: ");
                                order.put("Pedały", scanner.nextLine());
                                System.out.print("Podaj kierownicę: ");
                                order.put("Kierownica", scanner.nextLine());
                                System.out.print("Podaj dzwonek: ");
                                order.put("Dzwonek", scanner.nextLine());
                                System.out.print("Podaj światła: ");
                                order.put("Światła", scanner.nextLine());

                                orderManager.addOrder(order);
                                System.out.println("Zamówienie zostało złożone.");
                            } else if (employeeChoice == 2) {
                                certificateManager.listCertificates();
                            } else if (employeeChoice == 3) {
                                System.out.println("Wylogowano.");
                                break;
                            } else {
                                System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.");
                            }
                        }
                    }
                } else {
                    System.out.println("Nieprawidłowa nazwa użytkownika lub hasło. Spróbuj ponownie.");
                }

            } else if (choice == 3) {
                System.out.println("Zamykanie systemu. Do widzenia!");
                break;
            } else {
                System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.");
            }
        }

        scanner.close();
    }
}