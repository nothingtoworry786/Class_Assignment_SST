package org.example.Assignment1.Answers.Ex5;

import java.nio.charset.StandardCharsets;

public class PdfEncoder implements FormatEncoder {

    @Override
    public ExportResult encode(ExportRequest req) {

        if (req.body.length() > 20) {
            throw new IllegalArgumentException(
                    "PDF cannot handle content > 20 chars"
            );
        }

        String fakePdf =
                "PDF(" + req.title + "):" + req.body;

        return new ExportResult(
                "application/pdf",
                fakePdf.getBytes(StandardCharsets.UTF_8)
        );
    }
}
