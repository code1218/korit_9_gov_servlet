package com.korit.servlet_study.ch03;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserRepository {
    private static UserRepository instance;
    private List<User> users;
    private Long autoId = 0l;

    private UserRepository() {
        users = new ArrayList<>();
    }

    public static UserRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void insert(User user) {
        user.setId(++autoId);
        users.add(user);
    }

    public User findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public User findByUsernameNonOptional(String username) {
        List<User> foundUsers = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .toList();
        if (foundUsers.isEmpty()) {
            return null;
        }
        return foundUsers.get(0);
    }

    public List<User> findAll() {
        return users;
    }

}
