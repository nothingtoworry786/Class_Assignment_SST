package org.example.Assignment1.Answers.Ex5;

import java.nio.charset.StandardCharsets;

public class CsvEncoder implements FormatEncoder {

    @Override
    public ExportResult encode(ExportRequest req) {

        String safeTitle = escape(req.title);
        String safeBody = escape(req.body);

        String csv =
                "title,body\n" +
                safeTitle + "," +
                safeBody + "\n";

        return new ExportResult(
                "text/csv",
                csv.getBytes(StandardCharsets.UTF_8)
        );
    }

    private String escape(String s) {
        return "\"" + s.replace("\"", "\"\"") + "\"";
    }
}
