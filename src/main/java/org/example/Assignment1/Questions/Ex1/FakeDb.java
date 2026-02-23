package org.example.Assignment1.Questions.Ex1;

import java.util.*;

public class FakeDb {
    private final List<StudentRecord> rows = new ArrayList<>();

    public void save(StudentRecord r) { rows.add(r); }
    public int count() { return rows.size(); }
    public List<StudentRecord> all() { return Collections.unmodifiableList(rows); }
}
