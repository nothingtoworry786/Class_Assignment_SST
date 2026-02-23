package org.example.Assignment1.Questions.Ex9;

public class ReportWriter {
    public String write(Submission s, int plag, int code) {
        // writes to a pretend file name
        return "report-" + s.roll + ".txt";
    }
}
