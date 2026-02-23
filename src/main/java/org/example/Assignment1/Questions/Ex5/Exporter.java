package org.example.Assignment1.Questions.Ex5;

public abstract class Exporter {
    // implied "contract" but not enforced (smell)
    public abstract ExportResult export(ExportRequest req);
}
