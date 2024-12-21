package pl.gornik.szynal;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Authentication auth = new Authentication();
        Warehouse warehouse = new Warehouse();

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
                        System.out.println("Dostęp do: Zamówień części, statusu produkcji, opłat, tworzenia produktów.");

                    } else if ("employee".equals(loggedInUser.getRole())) {
                        while (true) {
                            System.out.println("\nMenu Pracownika:");
                            System.out.println("1. Dostęp do magazynu");
                            System.out.println("2. Wyloguj się");
                            System.out.print("Wybierz opcję: ");

                            int empChoice = scanner.nextInt();
                            scanner.nextLine();

                            if (empChoice == 1) {
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
                            } else if (empChoice == 2) {
                                System.out.println("Wylogowywanie...");
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