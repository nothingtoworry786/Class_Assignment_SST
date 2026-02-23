package org.example.Assignment1.Answers.Ex1;

import java.util.ArrayList;
import java.util.*;

public class StudentValidator {

    public List<String> validate(ParsedStudent s) {

        List<String> errors = new ArrayList<>();

        if (s.name.isBlank())
            errors.add("name is required");

        if (s.email.isBlank() || !s.email.contains("@"))
            errors.add("email is invalid");

        if (s.phone.isBlank() || !s.phone.chars().allMatch(Character::isDigit))
            errors.add("phone is invalid");

        if (!(s.program.equals("CSE") ||
                s.program.equals("AI") ||
                s.program.equals("SWE")))
            errors.add("program is invalid");

        return errors;
    }
}