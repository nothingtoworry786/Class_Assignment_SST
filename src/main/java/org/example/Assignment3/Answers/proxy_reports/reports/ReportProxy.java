package org.example.Assignment3.Answers.proxy_reports.reports;

public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();

    private RealReport realReport; // lazily created and cached

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("ACCESS DENIED for user=" + user.getName()
                    + " to classification=" + classification);
            return;
        }

        if (realReport == null) {
            System.out.println("[proxy] creating RealReport for " + reportId);
            realReport = new RealReport(reportId, title, classification);
        }

        realReport.display(user);
    }
}
