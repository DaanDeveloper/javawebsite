package tm.itbachelors.projectstore.model;

// Daan Borghs r0986005

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee extends Person {
    private LocalDate startDate;
    private boolean jobStudent;

    public Employee(String firstName, String surName) {
        super(firstName, surName);
        this.startDate = LocalDate.now();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isJobStudent() {
        return jobStudent;
    }

    public void setJobStudent(boolean jobStudent) {
        this.jobStudent = jobStudent;
    }

    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String jobStudentStr = jobStudent ? " (job student)" : "";
        return "Employee " + super.toString() + jobStudentStr + " is employed since " + startDate.format(dtf);
    }
}