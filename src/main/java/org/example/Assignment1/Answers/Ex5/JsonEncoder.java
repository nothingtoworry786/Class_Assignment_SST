package org.example.Assignment1.Answers.Ex5;

import java.nio.charset.StandardCharsets;

public class JsonEncoder implements FormatEncoder {

    @Override
    public ExportResult encode(ExportRequest req) {

        String json =
                "{\"title\":\"" + escape(req.title) +
                "\",\"body\":\"" + escape(req.body) +
                "\"}";

        return new ExportResult(
                "application/json",
                json.getBytes(StandardCharsets.UTF_8)
        );
    }

    private String escape(String s) {
        return s.replace("\"", "\\\"");
    }
}
