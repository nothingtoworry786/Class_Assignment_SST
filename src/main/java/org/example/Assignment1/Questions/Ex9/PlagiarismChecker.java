package org.example.Assignment1.Questions.Ex9;

public class PlagiarismChecker {
    public int check(Submission s) {
        // fake score: lower is "better", but pipeline adds it anyway (smell)
        return (s.code.contains("class") ? 12 : 40);
    }
}
