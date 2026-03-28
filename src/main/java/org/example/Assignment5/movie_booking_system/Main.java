package org.example.Assignment5.movie_booking_system;

import java.util.List;

import org.example.Assignment5.movie_booking_system.initializer.DataInitializer;
import org.example.Assignment5.movie_booking_system.models.Booking;
import org.example.Assignment5.movie_booking_system.models.Payment;
import org.example.Assignment5.movie_booking_system.models.Show;
import org.example.Assignment5.movie_booking_system.services.BookingService;
import org.example.Assignment5.movie_booking_system.services.EmailNotificationService;
import org.example.Assignment5.movie_booking_system.services.NotificationService;
import org.example.Assignment5.movie_booking_system.services.PaymentService;
import org.example.Assignment5.movie_booking_system.services.SeatLockService;
import org.example.Assignment5.movie_booking_system.storage.BookingStore;
import org.example.Assignment5.movie_booking_system.storage.MovieStore;
import org.example.Assignment5.movie_booking_system.storage.SeatLockStore;
import org.example.Assignment5.movie_booking_system.storage.ShowStore;
import org.example.Assignment5.movie_booking_system.storage.UserStore;
import org.example.Assignment5.movie_booking_system.strategies.UPIPayment;

public class Main {
    public static void main(String[] args) {
        UserStore userStore = new UserStore();
        MovieStore movieStore = new MovieStore();
        ShowStore showStore = new ShowStore();
        BookingStore bookingStore = new BookingStore();
        SeatLockStore seatLockStore = new SeatLockStore();

        String userId = DataInitializer.initialize(userStore, movieStore, showStore);

        SeatLockService seatLockService = new SeatLockService(seatLockStore, 10);
        PaymentService paymentService = new PaymentService();
        NotificationService notificationService = new EmailNotificationService();
        BookingService bookingService = new BookingService(
                userStore,
                showStore,
                bookingStore,
                seatLockService,
                paymentService,
                notificationService
        );

        Show show = showStore.getAll().get(0);
        List<String> seatsToBook = show.getShowSeats().stream().limit(2).map(s -> s.getId()).toList();

        Booking booking = bookingService.createBooking(userId, show.getId(), seatsToBook);
        Payment payment = bookingService.confirmBooking(booking.getId(), new UPIPayment());

        System.out.println("BookingId: " + booking.getId());
        System.out.println("BookingStatus: " + booking.getStatus());
        System.out.println("PaymentStatus: " + payment.getStatus());
        System.out.println("AmountPaid: " + payment.getAmount());
    }
}
