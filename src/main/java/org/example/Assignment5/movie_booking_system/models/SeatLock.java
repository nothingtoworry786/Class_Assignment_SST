package org.example.Assignment5.movie_booking_system.models;

import java.time.LocalDateTime;

public class SeatLock {
    private final String showId;
    private final String showSeatId;
    private final String userId;
    private final LocalDateTime lockedAt;
    private final LocalDateTime expiresAt;

    public SeatLock(String showId, String showSeatId, String userId, LocalDateTime lockedAt, LocalDateTime expiresAt) {
        this.showId = showId;
        this.showSeatId = showSeatId;
        this.userId = userId;
        this.lockedAt = lockedAt;
        this.expiresAt = expiresAt;
    }

    public String getShowId() {
        return showId;
    }

    public String getShowSeatId() {
        return showSeatId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getLockedAt() {
        return lockedAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }
}
