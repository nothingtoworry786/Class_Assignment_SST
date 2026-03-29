package org.example.Assignment5.movie_booking_system.models;

import java.time.LocalDateTime;
import java.util.List;

public class Show {
    private final String id;
    private final Movie movie;
    private final Screen screen;
    private final LocalDateTime startTime;
    private final List<ShowSeat> showSeats;

    public Show(String id, Movie movie, Screen screen, LocalDateTime startTime, List<ShowSeat> showSeats) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.showSeats = showSeats;
    }

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public List<ShowSeat> getShowSeats() {
        return showSeats;
    }
}
