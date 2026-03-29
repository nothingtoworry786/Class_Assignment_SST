package org.example.Assignment5.movie_booking_system.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.example.Assignment5.movie_booking_system.models.Movie;

public class MovieStore {
    private final Map<String, Movie> moviesById = new HashMap<>();

    public void add(Movie movie) {
        moviesById.put(movie.getId(), movie);
    }

    public Optional<Movie> getById(String movieId) {
        return Optional.ofNullable(moviesById.get(movieId));
    }

    public List<Movie> getAll() {
        return new ArrayList<>(moviesById.values());
    }
}
