// src/main/java/com/example/demo/service/UserService.java
package com.sampletest.demo.service;


import com.sampletest.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    private long nextId = 1;

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
    }

    public User createUser(User user) {
        user.setId(nextId++);
        users.add(user);
        return user;
    }

    public User updateUser(Long id, User user) {
        Optional<User> existing = users.stream().filter(u -> u.getId().equals(id)).findFirst();
        if (existing.isPresent()) {
            User u = existing.get();
            u.setName(user.getName());
            u.setEmail(user.getEmail());
            return u;
        }
        return null;
    }

    public void deleteUser(Long id) {
        users.removeIf(u -> u.getId().equals(id));
    }
}
