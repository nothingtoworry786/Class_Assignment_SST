package org.example.Assignment5.movie_booking_system.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Theater {
    private final String id;
    private final String name;
    private final String location;
    private final List<Screen> screens;

    public Theater(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.screens = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<Screen> getScreens() {
        return Collections.unmodifiableList(screens);
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }
}
