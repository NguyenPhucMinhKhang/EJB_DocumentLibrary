package com.example.ejb;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserBean {

    private List<User> users = new ArrayList<>();

    public UserBean() {
        // Adding some fake users
        users.add(new User(1L, "A", "user1", "password1", "user1@example.com"));
        users.add(new User(2L, "B", "user2", "password2", "user2@example.com"));
        users.add(new User(3L, "C", "user3", "password3", "user3@example.com"));
        users.add(new User(4L, "D", "user4", "password4", "user4@example.com"));
        users.add(new User(5L, "E", "user5", "password5", "user5@example.com"));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        return users.stream()
            .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
            .findFirst()
            .orElse(null);
    }
}