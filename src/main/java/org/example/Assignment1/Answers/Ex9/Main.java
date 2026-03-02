package org.example.Assignment1.Answers.Ex9;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Evaluation Pipeline ===");
        Submission sub = new Submission("23BCS1007", "public class A{}", "A.java");
        EvaluationPipeline pipeline=new EvaluationPipeline(new PlagiarismChecker(),new CodeGrader(),new ReportWriter(),new Rubric());
        pipeline.evaluate(sub);
    }
}
