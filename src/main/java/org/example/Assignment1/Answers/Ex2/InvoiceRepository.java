package org.example.Assignment1.Answers.Ex2;

public interface InvoiceRepository {

    void save(String name, String content);

    int countLines(String name);
}
