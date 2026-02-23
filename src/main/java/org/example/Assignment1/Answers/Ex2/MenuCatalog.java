package org.example.Assignment1.Answers.Ex2;

import java.util.*;

public class MenuCatalog {

    private final Map<String, MenuItem> items = new LinkedHashMap<>();

    public void add(MenuItem item) {
        items.put(item.id, item);
    }

    public MenuItem find(String id) {
        return items.get(id);
    }
}
