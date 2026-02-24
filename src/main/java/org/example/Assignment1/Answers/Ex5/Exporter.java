package org.example.Assignment1.Answers.Ex5;

public class Exporter {

    private final FormatEncoder encoder;

    public Exporter(FormatEncoder encoder) {
        this.encoder = encoder;
    }
    public ExportResult export(ExportRequest req) {

        if (req == null) {
            throw new IllegalArgumentException("request cannot be null");
        }

        if (req.title == null) {
            throw new IllegalArgumentException("title cannot be null");
        }

        if (req.body == null) {
            throw new IllegalArgumentException("body cannot be null");
        }

        return encoder.encode(req);
    }
}
