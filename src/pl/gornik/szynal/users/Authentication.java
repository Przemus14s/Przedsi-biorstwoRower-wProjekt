package pl.gornik.szynal.users;

import java.util.*;

public class Authentication {
    private final List<User> users;

    public Authentication() {
        users = new ArrayList<>();
    }

    public void register(String username, String password, String role) {
        users.add(new User(username, password, role));
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}