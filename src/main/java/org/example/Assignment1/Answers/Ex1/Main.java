package org.example.Assignment1.Answers.Ex1;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Student Onboarding ===");

        FakeDb db = new FakeDb();

        OnboardingService service =
                new OnboardingService(
                        new StudentInputParser(),
                        new StudentValidator(),
                        db,
                        new OnboardingPrinter()
                );

        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";

        service.registerFromRawInput(raw);

        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.print(TextTable.render3(db));
    }
}