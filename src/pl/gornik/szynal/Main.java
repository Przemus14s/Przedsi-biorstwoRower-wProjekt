import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Bike Factory ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter role (employee/manager): ");
                String role = scanner.nextLine();

                auth.register(username, password, role);
                System.out.println("Registration successful!");

            } else if (choice == 2) {
                System.out.print("Username: ");
                String username = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();

                User loggedInUser = auth.login(username, password);

                if (loggedInUser != null) {
                    System.out.println("Login successful! Welcome, " + loggedInUser.getRole() + " " + loggedInUser.getUsername() + ".");

                    if ("manager".equals(loggedInUser.getRole())) {
                        System.out.println("Access to: Parts orders, production status, payments, product creation.");
                        // Add manager-specific functionality here
                    } else if ("employee".equals(loggedInUser.getRole())) {
                        System.out.println("Access to: Place orders, production status, product evaluation, warehouse.");

                        while (true) {
                            System.out.println("\nEmployee Menu:");
                            System.out.println("1. Access Warehouse");
                            System.out.println("2. Logout");
                            System.out.print("Choose an option: ");

                            int empChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            if (empChoice == 1) {
                                while (true) {
                                    System.out.println("\nWarehouse Menu:");
                                    System.out.println("1. Add Part");
                                    System.out.println("2. List Parts");
                                    System.out.println("3. Back");
                                    System.out.print("Choose an option: ");

                                    int whChoice = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline

                                    if (whChoice == 1) {
                                        System.out.print("Enter part name to add: ");
                                        String part = scanner.nextLine();
                                        warehouse.addPart(part);
                                        System.out.println("Part added successfully.");
                                    } else if (whChoice == 2) {
                                        warehouse.listParts();
                                    } else if (whChoice == 3) {
                                        break;
                                    } else {
                                        System.out.println("Invalid option. Please try again.");
                                    }
                                }
                            } else if (empChoice == 2) {
                                System.out.println("Logging out...");
                                break;
                            } else {
                                System.out.println("Invalid option. Please try again.");
                            }
                        }
                    }
                } else {
                    System.out.println("Invalid username or password. Please try again.");
                }

            } else if (choice == 3) {
                System.out.println("Exiting the system. Goodbye!");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}