package pl.gornik.szynal.users;

import pl.gornik.szynal.exceptions.ValidationException;

public class User {
    private final String username;
    private String password;
    private Role role;

    public User(String username, String password, Role role) {
        if (username == null || username.isEmpty()) {
            throw new ValidationException("Nazwa użytkownika nie może być pusta.");
        }
        if (password == null || password.length() < 6) {
            throw new ValidationException("Hasło musi mieć co najmniej 6 znaków.");
        }
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        if (newPassword == null || newPassword.length() < 6) {
            throw new ValidationException("Hasło musi mieć co najmniej 6 znaków.");
        }
        this.password = newPassword;
    }

    public Role getRole() {
        return role;
    }


    public void setRole(Role newRole) {
        if (newRole == null) {
            throw new ValidationException("Rola nie może być pusta.");
        }
        this.role = newRole;
    }
}
