package org.example.Assignment1.Answers.Ex2;


import java.util.*;

public class PricingService {

    private final TaxPolicy taxPolicy;
    private final DiscountPolicy discountPolicy;

    public PricingService(TaxPolicy taxPolicy,
                          DiscountPolicy discountPolicy) {
        this.taxPolicy = taxPolicy;
        this.discountPolicy = discountPolicy;
    }

    public Invoice generateInvoice(String id,
                                   String customerType,
                                   List<OrderLine> lines,
                                   MenuCatalog menu) {

        List<Invoice.Line> invoiceLines = new ArrayList<>();
        double subtotal = 0.0;

        for (OrderLine l : lines) {
            MenuItem item = menu.find(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            invoiceLines.add(
                    new Invoice.Line(item.name, l.qty, lineTotal)
            );
        }

        double taxPct = taxPolicy.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);

        double discount =
                discountPolicy.discount(customerType, subtotal, lines.size());

        double total = subtotal + tax - discount;

        return new Invoice(id, invoiceLines,
                subtotal, taxPct, tax, discount, total);
    }
}
