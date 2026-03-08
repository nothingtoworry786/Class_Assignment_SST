package org.example.Assignment3.Answers.proxy_reports.reports;

public class ReportViewer {

    public void open(Report report, User user) {
        report.display(user);
    }
}
