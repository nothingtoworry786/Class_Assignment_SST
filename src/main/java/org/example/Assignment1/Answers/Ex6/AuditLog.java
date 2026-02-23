package org.example.Assignment1.Answers.Ex6;

import java.util.ArrayList;
import java.util.List;

public class AuditLog {

    private final List<String> entries = new ArrayList<>();

    public void add(String e) {
        entries.add(e);
    }

    public int size() {
        return entries.size();
    }
}
