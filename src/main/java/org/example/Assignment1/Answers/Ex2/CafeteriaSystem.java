package org.example.Assignment1.Answers.Ex2;

import java.util.*;

public class CafeteriaSystem {

    private final MenuCatalog menu;
    private final PricingService pricing;
    private final InvoiceFormatter formatter;
    private final InvoiceRepository repository;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(MenuCatalog menu,
                           PricingService pricing,
                           InvoiceFormatter formatter,
                           InvoiceRepository repository) {
        this.menu = menu;
        this.pricing = pricing;
        this.formatter = formatter;
        this.repository = repository;
    }

    public void checkout(String customerType, List<OrderLine> lines) {

        String invId = "INV-" + (++invoiceSeq);

        Invoice invoice = pricing.generateInvoice(
                invId,
                customerType,
                lines,
                menu
        );

        String printable = formatter.format(invoice);

        System.out.print(printable);

        repository.save(invId, printable);
        System.out.println("Saved invoice: " + invId +
                " (lines=" + repository.countLines(invId) + ")");
    }
}
