package com.example.sms.dao;
import com.example.sms.model.Student;
import java.sql.*; import java.util.ArrayList; import java.util.List;
public class StudentDAO {
    public List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT student_id, name, department, year FROM students ORDER BY student_id";
        try (Connection c = DBConnection.getConnection(); Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Student(rs.getInt("student_id"), rs.getString("name"), rs.getString("department"), rs.getInt("year")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    public Student findById(int id) {
        String sql = "SELECT student_id, name, department, year FROM students WHERE student_id = ?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return new Student(rs.getInt("student_id"), rs.getString("name"), rs.getString("department"), rs.getInt("year"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
    public void insert(Student s) {
        String sql = "INSERT INTO students(name, department, year) VALUES (?, ?, ?)";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, s.getName()); ps.setString(2, s.getDepartment()); ps.setInt(3, s.getYear());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) { if (keys.next()) s.setStudentId(keys.getInt(1)); }
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
