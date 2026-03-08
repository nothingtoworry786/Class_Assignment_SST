package org.example.Assignment3.Answers.flyweight_markers.map;

import java.util.List;

public class App {

    public static void main(String[] args) {
        int n = 30_000;

        MapDataSource ds = new MapDataSource();
        List<MapMarker> markers = ds.loadMarkers(n);

        new MapRenderer().render(markers);

        System.out.println();
        System.out.println("Run QuickCheck (Answers) to verify Flyweight sharing:");
        System.out.println("  java org.example.Assignment3.Answers.flyweight_markers.map.QuickCheck");
    }
}
