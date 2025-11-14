package com.example.sms.dao;
import java.sql.Connection; import java.sql.DriverManager; import java.sql.SQLException; import java.sql.Statement;
import java.nio.file.Files; import java.nio.file.Path;
public class DBConnection {
    private static Connection conn;
    private static final String DB_DIR = "data";
    private static final String DB_FILE = DB_DIR + "/sms.db";
    private static final String URL = "jdbc:sqlite:" + DB_FILE;
    public static void initDatabase() {
        try {
            Path d = Path.of(DB_DIR);
            if (!Files.exists(d)) Files.createDirectories(d);
            conn = DriverManager.getConnection(URL);
            try (Statement st = conn.createStatement()) {
                st.execute("CREATE TABLE IF NOT EXISTS students (student_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, department TEXT, year INTEGER);");
                st.execute("INSERT INTO students(name, department, year) SELECT 'Alice Johnson','Computer Science',2 WHERE NOT EXISTS(SELECT 1 FROM students WHERE name='Alice Johnson');");
                st.execute("INSERT INTO students(name, department, year) SELECT 'Bob Singh','Electrical',3 WHERE NOT EXISTS(SELECT 1 FROM students WHERE name='Bob Singh');");
                st.execute("INSERT INTO students(name, department, year) SELECT 'Carla Mehta','Computer Science',2 WHERE NOT EXISTS(SELECT 1 FROM students WHERE name='Carla Mehta');");
            }
            System.out.println("Database initialized: " + DB_FILE);
        } catch (Exception e) {
            System.err.println("Failed to init DB: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) conn = DriverManager.getConnection(URL);
        return conn;
    }
    public static void close() {
        try { if (conn != null && !conn.isClosed()) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
}
