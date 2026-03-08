package org.example.Assignment3.Answers.proxy_reports.reports;

public class RealReport implements Report {

    private final String reportId;
    private final String title;
    private final String classification;

    private String cachedContent;

    public RealReport(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        String content = loadContentOnce();
        System.out.println("REPORT -> id=" + reportId
                + " title=" + title
                + " classification=" + classification
                + " openedBy=" + user.getName());
        System.out.println("CONTENT: " + content);
    }

    private String loadContentOnce() {
        if (cachedContent == null) {
            System.out.println("[disk] loading report " + reportId + " ...");
            try {
                Thread.sleep(120);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            cachedContent = "Internal report body for " + title;
        }
        return cachedContent;
    }

    public String getClassification() {
        return classification;
    }
}
