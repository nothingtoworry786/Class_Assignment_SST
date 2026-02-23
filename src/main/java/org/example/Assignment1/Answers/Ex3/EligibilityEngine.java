package org.example.Assignment1.Answers.Ex3;

import java.util.*;

public class EligibilityEngine {

    private final FakeEligibilityStore store;
    private final List<EligibilityRule> rules;

    public EligibilityEngine(FakeEligibilityStore store) {

        this.store = store;

        // Order matters – preserves original behavior
        this.rules = List.of(
                new DisciplinaryRule(),
                new CgrRule(8.0),
                new AttendanceRule(75),
                new CreditsRule(20)
        );
    }

    public void runAndPrint(StudentProfile s) {

        ReportPrinter printer = new ReportPrinter();

        EligibilityEngineResult result = evaluate(s);

        printer.print(s, result);

        store.save(s.rollNo, result.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {

        List<String> reasons = new ArrayList<>();
        String status = "ELIGIBLE";

        for (EligibilityRule rule : rules) {

            Optional<String> failure = rule.check(s);

            if (failure.isPresent()) {
                status = "NOT_ELIGIBLE";
                reasons.add(failure.get());
                break; // preserve original else-if behavior
            }
        }

        return new EligibilityEngineResult(status, reasons);
    }
}
