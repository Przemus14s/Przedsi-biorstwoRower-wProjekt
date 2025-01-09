package pl.gornik.szynal.users;

import pl.gornik.szynal.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class Authentication {
    private final List<User> users;

    public Authentication() {
        users = new ArrayList<>();
    }

    public void register(String username, String password, Role role) {
        if (users.stream().anyMatch(user -> user.getUsername().equals(username))) {
            throw new ValidationException("Użytkownik o tej nazwie już istnieje.");
        }
        users.add(new User(username, password, role));
    }

    public User login(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new ValidationException("Nieprawidłowa nazwa użytkownika lub hasło."));
    }

    public Role getRoleFromString(String roleInput) {
        switch (roleInput.toLowerCase()) {
            case "menadżer":
                return Role.MANAGER;
            case "pracownik":
                return Role.EMPLOYEE;
            case "klient":
                return Role.CLIENT;
            default:
                throw new ValidationException("Nieprawidłowa rola. Rejestracja nie powiodła się.");
        }
    }


    public void changePassword(String username, String oldPassword, String newPassword) {
        User user = users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new ValidationException("Użytkownik nie istnieje."));


        if (!user.getPassword().equals(oldPassword)) {
            throw new ValidationException("Błędne stare hasło.");
        }


        user.setPassword(newPassword);
        System.out.println("Hasło zostało zmienione pomyślnie.");
    }
}