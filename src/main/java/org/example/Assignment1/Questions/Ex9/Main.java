package org.example.Assignment1.Questions.Ex9;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Evaluation Pipeline ===");
        Submission sub = new Submission("23BCS1007", "public class A{}", "A.java");
        new EvaluationPipeline().evaluate(sub);
    }
}
