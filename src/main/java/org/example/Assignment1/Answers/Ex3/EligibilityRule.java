package org.example.Assignment1.Answers.Ex3;

import java.util.Optional;

public interface EligibilityRule {

    Optional<String> check(StudentProfile student);

}
