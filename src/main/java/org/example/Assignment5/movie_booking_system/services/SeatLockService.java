package org.example.Assignment5.movie_booking_system.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.example.Assignment5.movie_booking_system.enums.SeatStatus;
import org.example.Assignment5.movie_booking_system.models.SeatLock;
import org.example.Assignment5.movie_booking_system.models.Show;
import org.example.Assignment5.movie_booking_system.models.ShowSeat;
import org.example.Assignment5.movie_booking_system.storage.SeatLockStore;

public class SeatLockService {
    private final SeatLockStore seatLockStore;
    private final int lockMinutes;

    public SeatLockService(SeatLockStore seatLockStore, int lockMinutes) {
        this.seatLockStore = seatLockStore;
        this.lockMinutes = lockMinutes;
    }

    public synchronized boolean lockSeats(Show show, List<String> showSeatIds, String userId) {
        releaseExpiredLocks(show);
        Map<String, ShowSeat> byId = show.getShowSeats().stream()
                .collect(Collectors.toMap(ShowSeat::getId, Function.identity()));

        for (String showSeatId : showSeatIds) {
            ShowSeat seat = byId.get(showSeatId);
            if (seat == null || seat.getStatus() != SeatStatus.AVAILABLE || seatLockStore.getBySeat(show.getId(), showSeatId).isPresent()) {
                return false;
            }
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiry = now.plusMinutes(lockMinutes);
        for (String showSeatId : showSeatIds) {
            ShowSeat seat = byId.get(showSeatId);
            seat.setStatus(SeatStatus.LOCKED);
            seatLockStore.add(new SeatLock(show.getId(), showSeatId, userId, now, expiry));
        }
        return true;
    }

    public synchronized boolean validateLockOwner(Show show, List<String> showSeatIds, String userId) {
        releaseExpiredLocks(show);
        for (String showSeatId : showSeatIds) {
            SeatLock seatLock = seatLockStore.getBySeat(show.getId(), showSeatId).orElse(null);
            if (seatLock == null || !seatLock.getUserId().equals(userId) || seatLock.isExpired()) {
                return false;
            }
        }
        return true;
    }

    public synchronized void unlockSeats(Show show, List<String> showSeatIds) {
        Map<String, ShowSeat> byId = show.getShowSeats().stream()
                .collect(Collectors.toMap(ShowSeat::getId, Function.identity()));
        for (String showSeatId : showSeatIds) {
            ShowSeat seat = byId.get(showSeatId);
            if (seat != null && seat.getStatus() != SeatStatus.BOOKED) {
                seat.setStatus(SeatStatus.AVAILABLE);
            }
            seatLockStore.remove(show.getId(), showSeatId);
        }
    }

    public synchronized void markSeatsBooked(Show show, List<String> showSeatIds) {
        Map<String, ShowSeat> byId = show.getShowSeats().stream()
                .collect(Collectors.toMap(ShowSeat::getId, Function.identity()));
        for (String showSeatId : showSeatIds) {
            ShowSeat seat = byId.get(showSeatId);
            if (seat != null) {
                seat.setStatus(SeatStatus.BOOKED);
            }
            seatLockStore.remove(show.getId(), showSeatId);
        }
    }

    private void releaseExpiredLocks(Show show) {
        show.getShowSeats().forEach(showSeat -> {
            seatLockStore.getBySeat(show.getId(), showSeat.getId()).ifPresent(lock -> {
                if (lock.isExpired()) {
                    showSeat.setStatus(SeatStatus.AVAILABLE);
                    seatLockStore.remove(show.getId(), showSeat.getId());
                }
            });
        });
    }
}
