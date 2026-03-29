package org.example.Assignment5.movie_booking_system.initializer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.example.Assignment5.movie_booking_system.enums.SeatType;
import org.example.Assignment5.movie_booking_system.models.Movie;
import org.example.Assignment5.movie_booking_system.models.Screen;
import org.example.Assignment5.movie_booking_system.models.Seat;
import org.example.Assignment5.movie_booking_system.models.Show;
import org.example.Assignment5.movie_booking_system.models.ShowSeat;
import org.example.Assignment5.movie_booking_system.models.Theater;
import org.example.Assignment5.movie_booking_system.models.User;
import org.example.Assignment5.movie_booking_system.storage.MovieStore;
import org.example.Assignment5.movie_booking_system.storage.ShowStore;
import org.example.Assignment5.movie_booking_system.storage.UserStore;
import org.example.Assignment5.movie_booking_system.utils.IdGenerator;

public class DataInitializer {
    private DataInitializer() {
    }

    public static String initialize(UserStore userStore, MovieStore movieStore, ShowStore showStore) {
        User user = new User(IdGenerator.nextId("USR"), "Alice", "alice@example.com");
        userStore.add(user);

        Movie movie = new Movie(IdGenerator.nextId("MOV"), "Inception", "Sci-Fi", 148);
        movieStore.add(movie);

        Theater theater = new Theater(IdGenerator.nextId("THR"), "PVR Central", "Downtown");
        Screen screen = new Screen(IdGenerator.nextId("SCR"), "Screen-1");
        theater.addScreen(screen);

        List<Seat> seats = new ArrayList<>();
        for (int row = 1; row <= 2; row++) {
            for (int number = 1; number <= 5; number++) {
                SeatType type = row == 1 ? SeatType.PREMIUM : SeatType.REGULAR;
                double basePrice = type == SeatType.PREMIUM ? 300.0 : 180.0;
                Seat seat = new Seat(IdGenerator.nextId("SET"), row, number, type, basePrice);
                seats.add(seat);
                screen.addSeat(seat);
            }
        }

        List<ShowSeat> showSeats = new ArrayList<>();
        for (Seat seat : seats) {
            showSeats.add(new ShowSeat(IdGenerator.nextId("SST"), seat, seat.getBasePrice()));
        }

        Show show = new Show(IdGenerator.nextId("SHW"), movie, screen, LocalDateTime.now().plusHours(2), showSeats);
        showStore.add(show);

        return user.getId();
    }
}
