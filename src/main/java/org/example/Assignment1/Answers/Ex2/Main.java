package org.example.Assignment1.Answers.Ex2;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Cafeteria Billing ===");

        MenuCatalog menu = new MenuCatalog();
        menu.add(new MenuItem("M1", "Veg Thali", 80.00));
        menu.add(new MenuItem("C1", "Coffee", 30.00));
        menu.add(new MenuItem("S1", "Sandwich", 60.00));

        TaxPolicy taxPolicy = new DefaultTaxPolicy();
        DiscountPolicy discountPolicy = new DefaultDiscountPolicy();

        PricingService pricing =
                new PricingService(taxPolicy, discountPolicy);

        InvoiceRepository repo = new FileStore();

        CafeteriaSystem system =
                new CafeteriaSystem(menu, pricing, new InvoiceFormatter(), repo);

        List<OrderLine> order = List.of(
                new OrderLine("M1", 2),
                new OrderLine("C1", 1)
        );

        system.checkout("student", order);
    }
}
