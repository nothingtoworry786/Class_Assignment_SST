package org.example.Assignment1.Answers.Ex2;

import java.util.*;

public class Invoice {

    public static class Line {
        public final String name;
        public final int qty;
        public final double lineTotal;

        public Line(String name, int qty, double lineTotal) {
            this.name = name;
            this.qty = qty;
            this.lineTotal = lineTotal;
        }
    }

    public final String id;
    public final List<Line> lines;
    public final double subtotal;
    public final double taxPercent;
    public final double tax;
    public final double discount;
    public final double total;

    public Invoice(String id,
                   List<Line> lines,
                   double subtotal,
                   double taxPercent,
                   double tax,
                   double discount,
                   double total) {
        this.id = id;
        this.lines = lines;
        this.subtotal = subtotal;
        this.taxPercent = taxPercent;
        this.tax = tax;
        this.discount = discount;
        this.total = total;
    }
}
