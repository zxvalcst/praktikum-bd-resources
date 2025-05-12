package com.example.bdsqltester.dtos;

public class Grade {
    private Long userId;
    private Long assignmentId;
    private Double grade;
    private String username; // Tambahkan atribut username

    public Grade() {
    }

    public Grade(Long userId, Long assignmentId, Double grade) {
        this.userId = userId;
        this.assignmentId = assignmentId;
        this.grade = grade;
    }

    // Constructor untuk membaca dari ResultSet (sesuaikan jika perlu)
    public Grade(java.sql.ResultSet rs) throws java.sql.SQLException {
        this.userId = rs.getLong("user_id");
        this.assignmentId = rs.getLong("assignment_id");
        this.grade = rs.getDouble("grade");
        // Anda perlu mengambil username saat query di AdminController diubah
        // this.username = rs.getString("username");
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}