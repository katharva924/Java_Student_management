package com.example.sms.model;
public class Student {
    private int studentId;
    private String name;
    private String department;
    private int year;
    public Student(int studentId, String name, String department, int year) {
        this.studentId = studentId; this.name = name; this.department = department; this.year = year;
    }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    @Override public String toString() {
        return String.format("Student{id=%d, name='%s', dept='%s', year=%d}", studentId, name, department, year);
    }
}
