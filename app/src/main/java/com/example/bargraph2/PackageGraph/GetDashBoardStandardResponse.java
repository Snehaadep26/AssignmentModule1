package com.example.bargraph2.PackageGraph;



import java.util.List;

public class GetDashBoardStandardResponse {
    public List<Object> subjects;
    public TodaysActivities todaysActivities;
    public List<Summary> summary;
    public int publishedCount;
    public int dueForSubmissionCount;
    public int returnedCount;
    public List<AllSubject> allSubjects;

    public GetDashBoardStandardResponse(List<Object> subjects, TodaysActivities todaysActivities, List<Summary> summary, int publishedCount, int dueForSubmissionCount, int returnedCount, List<AllSubject> allSubjects) {
        this.subjects = subjects;
        this.todaysActivities = todaysActivities;
        this.summary = summary;
        this.publishedCount = publishedCount;
        this.dueForSubmissionCount = dueForSubmissionCount;
        this.returnedCount = returnedCount;
        this.allSubjects = allSubjects;
    }

    public List<Object> getSubjects() {
        return subjects;
    }

    public TodaysActivities getTodaysActivities() {
        return todaysActivities;
    }

    public List<Summary> getSummary() {
        return summary;
    }

    public int getPublishedCount() {
        return publishedCount;
    }

    public int getDueForSubmissionCount() {
        return dueForSubmissionCount;
    }

    public int getReturnedCount() {
        return returnedCount;
    }

    public List<AllSubject> getAllSubjects() {
        return allSubjects;
    }
}


