package org.example.Assignment3.Answers.flyweight_markers.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight factory that caches MarkerStyle instances by a stable key.
 */
public class MarkerStyleFactory {

    private final Map<String, MarkerStyle> cache = new HashMap<>();

    public MarkerStyle get(String shape, String color, int size, boolean filled) {
        String key = shape + "|" + color + "|" + size + "|" + (filled ? "F" : "O");
        MarkerStyle existing = cache.get(key);
        if (existing != null) {
            return existing;
        }
        MarkerStyle created = new MarkerStyle(shape, color, size, filled);
        cache.put(key, created);
        return created;
    }

    public int cacheSize() {
        return cache.size();
    }
}
