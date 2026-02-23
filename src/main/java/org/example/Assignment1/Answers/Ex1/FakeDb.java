package org.example.Assignment1.Answers.Ex1;

import org.example.Assignment1.Answers.Ex1.StudentRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class FakeDb implements StudentRepository {

    private final List<StudentRecord> rows = new ArrayList<>();

    @Override
    public void save(StudentRecord r) {
        rows.add(r);
    }

    @Override
    public int count() {
        return rows.size();
    }

    public List<StudentRecord> all() {
        return Collections.unmodifiableList(rows);
    }
}