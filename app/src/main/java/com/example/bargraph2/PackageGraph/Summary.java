package com.example.bargraph2.PackageGraph;

public class Summary {
    public int overdue;
    public String unit;
    public String date;
    public int submitted;
    public int dueForSubmission;

    public Summary(int overdue, int submitted, int dueForSubmission) {
        this.overdue = overdue;
        this.unit = unit;
        this.date = date;
        this.submitted = submitted;
        this.dueForSubmission = dueForSubmission;
    }

    public int getOverdue() {
        return overdue;
    }

    public String getUnit() {
        return unit;
    }

    public String getDate() {
        return date;
    }

    public int getSubmitted() {
        return submitted;
    }

    public int getDueForSubmission() {
        return dueForSubmission;
    }
}
