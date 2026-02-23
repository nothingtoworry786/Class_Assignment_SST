package org.example.Assignment1.Questions.Ex6;

import java.util.*;

public class AuditLog {
    private final List<String> entries = new ArrayList<>();
    public void add(String e) { entries.add(e); }
    public int size() { return entries.size(); }
}
