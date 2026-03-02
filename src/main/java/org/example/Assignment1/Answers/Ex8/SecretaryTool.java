package org.example.Assignment1.Answers.Ex8;

public class SecretaryTool implements MinutesOperations {
    private final MinutesBook book;
    public SecretaryTool(MinutesBook book) { this.book = book; }

    @Override public void addMinutes(String text) { book.add(text); }

}
