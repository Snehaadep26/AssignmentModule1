package com.example.bargraph2.PackageGraph;

import java.util.Date;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */
public class Published {
    public int id;
    public String title;
    public Date dueDate;
    public String status;
    public String type;
    public Date publishDate;
    public String subjectName;
    public String subjectIcon;
    public int assignmentStudentId;
    public String assignmentStudentStatus;
    public Date returnedDate;
    public String teacherName;
    public String noOfQuestions;
    public String marks;
    public String countAttempted;
}
