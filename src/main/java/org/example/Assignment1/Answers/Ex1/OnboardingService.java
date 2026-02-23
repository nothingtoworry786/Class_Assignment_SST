package org.example.Assignment1.Answers.Ex1;
import java.util.List;

public class OnboardingService {

    private final StudentInputParser parser;
    private final StudentValidator validator;
    private final StudentRepository repository;
    private final OnboardingPrinter printer;

    public OnboardingService(StudentInputParser parser,
                             StudentValidator validator,
                             StudentRepository repository,
                             OnboardingPrinter printer) {
        this.parser = parser;
        this.validator = validator;
        this.repository = repository;
        this.printer = printer;
    }

    public void registerFromRawInput(String raw) {

        printer.printInput(raw);

        ParsedStudent parsed = parser.parse(raw);

        List<String> errors = validator.validate(parsed);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(repository.count());

        StudentRecord record = new StudentRecord(
                id,
                parsed.name,
                parsed.email,
                parsed.phone,
                parsed.program
        );

        repository.save(record);

        printer.printSuccess(record, repository.count());
    }
}
