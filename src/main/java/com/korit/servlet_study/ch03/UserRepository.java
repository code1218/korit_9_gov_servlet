package com.korit.servlet_study.ch03;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepository {
    private static UserRepository instance;
    private List<User> users;
    private Long autoId;

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
                .findFirst().get();
    }

    public List<User> findAll() {
        return users;
    }

}
