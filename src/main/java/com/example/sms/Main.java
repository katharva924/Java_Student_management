package com.example.sms;
import com.example.sms.dao.DBConnection;
import com.example.sms.dao.StudentDAO;
import com.example.sms.model.Student;
import java.util.List;
import java.util.Scanner;
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Starting Java Student Management System (SQLite)...");
        DBConnection.initDatabase();
        StudentDAO studentDAO = new StudentDAO();
        boolean running = true;
        while (running) {
            System.out.println("\nMenu:\n1. List students\n2. Add student\n3. Find student by ID\n4. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> {
                    List<Student> students = studentDAO.getAll();
                    System.out.println("-- Students --");
                    students.forEach(System.out::println);
                }
                case "2" -> {
                    System.out.print("Name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Department: ");
                    String dept = scanner.nextLine().trim();
                    System.out.print("Year: ");
                    int year = Integer.parseInt(scanner.nextLine().trim());
                    Student s = new Student(0, name, dept, year);
                    studentDAO.insert(s);
                    System.out.println("Inserted: " + s);
                }
                case "3" -> {
                    System.out.print("Student ID: ");
                    int id = Integer.parseInt(scanner.nextLine().trim());
                    Student s = studentDAO.findById(id);
                    if (s != null) System.out.println(s); else System.out.println("Not found");
                }
                case "4" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
        System.out.println("Goodbye!");
        DBConnection.close();
    }
}
