package org.example.Assignment5.movie_booking_system.storage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.example.Assignment5.movie_booking_system.models.User;

public class UserStore {
    private final Map<String, User> usersById = new HashMap<>();

    public void add(User user) {
        usersById.put(user.getId(), user);
    }

    public Optional<User> getById(String userId) {
        return Optional.ofNullable(usersById.get(userId));
    }
}
