package org.example.Assignment5.movie_booking_system.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.example.Assignment5.movie_booking_system.models.Show;

public class ShowStore {
    private final Map<String, Show> showsById = new HashMap<>();

    public void add(Show show) {
        showsById.put(show.getId(), show);
    }

    public Optional<Show> getById(String showId) {
        return Optional.ofNullable(showsById.get(showId));
    }

    public List<Show> getAll() {
        return new ArrayList<>(showsById.values());
    }
}
