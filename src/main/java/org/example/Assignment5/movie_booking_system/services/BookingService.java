package org.example.Assignment5.movie_booking_system.services;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.example.Assignment5.movie_booking_system.enums.BookingStatus;
import org.example.Assignment5.movie_booking_system.enums.PaymentStatus;
import org.example.Assignment5.movie_booking_system.models.Booking;
import org.example.Assignment5.movie_booking_system.models.Payment;
import org.example.Assignment5.movie_booking_system.models.Show;
import org.example.Assignment5.movie_booking_system.models.ShowSeat;
import org.example.Assignment5.movie_booking_system.models.User;
import org.example.Assignment5.movie_booking_system.storage.BookingStore;
import org.example.Assignment5.movie_booking_system.storage.ShowStore;
import org.example.Assignment5.movie_booking_system.storage.UserStore;
import org.example.Assignment5.movie_booking_system.strategies.PaymentStrategy;
import org.example.Assignment5.movie_booking_system.utils.IdGenerator;

public class BookingService {
    private final UserStore userStore;
    private final ShowStore showStore;
    private final BookingStore bookingStore;
    private final SeatLockService seatLockService;
    private final PaymentService paymentService;
    private final NotificationService notificationService;

    public BookingService(UserStore userStore,
                          ShowStore showStore,
                          BookingStore bookingStore,
                          SeatLockService seatLockService,
                          PaymentService paymentService,
                          NotificationService notificationService) {
        this.userStore = userStore;
        this.showStore = showStore;
        this.bookingStore = bookingStore;
        this.seatLockService = seatLockService;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }

    public Booking createBooking(String userId, String showId, List<String> showSeatIds) {
        User user = userStore.getById(userId).orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        Show show = showStore.getById(showId).orElseThrow(() -> new IllegalArgumentException("Show not found: " + showId));

        boolean locked = seatLockService.lockSeats(show, showSeatIds, userId);
        if (!locked) {
            throw new IllegalStateException("One or more seats are not available");
        }

        Map<String, ShowSeat> seatById = show.getShowSeats().stream()
                .collect(Collectors.toMap(ShowSeat::getId, Function.identity()));

        List<ShowSeat> bookedSeats = showSeatIds.stream().map(seatById::get).toList();
        double totalAmount = bookedSeats.stream().mapToDouble(ShowSeat::getPrice).sum();

        Booking booking = new Booking(IdGenerator.nextId("BKG"), user, show, bookedSeats, totalAmount);
        bookingStore.add(booking);
        return booking;
    }

    public Payment confirmBooking(String bookingId, PaymentStrategy paymentStrategy) {
        Booking booking = bookingStore.getById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found: " + bookingId));

        List<String> seatIds = booking.getSeats().stream().map(ShowSeat::getId).toList();
        boolean validOwner = seatLockService.validateLockOwner(booking.getShow(), seatIds, booking.getUser().getId());
        if (!validOwner) {
            booking.setStatus(BookingStatus.CANCELLED);
            bookingStore.update(booking);
            throw new IllegalStateException("Seat lock expired or held by another user");
        }

        Payment payment = paymentService.processPayment(booking.getId(), booking.getTotalAmount(), paymentStrategy);
        if (payment.getStatus() == PaymentStatus.COMPLETED) {
            seatLockService.markSeatsBooked(booking.getShow(), seatIds);
            booking.setStatus(BookingStatus.CONFIRMED);
            notificationService.sendBookingConfirmation(booking);
        } else {
            seatLockService.unlockSeats(booking.getShow(), seatIds);
            booking.setStatus(BookingStatus.CANCELLED);
            notificationService.sendCancellation(booking);
        }
        bookingStore.update(booking);
        return payment;
    }

    public void cancelBooking(String bookingId) {
        Booking booking = bookingStore.getById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found: " + bookingId));
        List<String> seatIds = booking.getSeats().stream().map(ShowSeat::getId).toList();
        seatLockService.unlockSeats(booking.getShow(), seatIds);
        booking.setStatus(BookingStatus.CANCELLED);
        bookingStore.update(booking);
        notificationService.sendCancellation(booking);
    }
}
