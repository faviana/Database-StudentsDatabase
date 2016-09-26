package com.ironyard.services;

import com.ironyard.data.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by favianalopez on 9/26/16.
 */
public class StudentService {

    public void createStudent(Student x) throws SQLException {
        Database myDba =  new Database();
        Connection conn = myDba.getConnection();
        PreparedStatement stmt = conn.prepareCall("INSERT into students VALUES (null, ?,?)");
        stmt.setString(1, x.getName());
        stmt.setInt(2, x.getAge());
        stmt.execute();
    }

    public Student getStudentByName (String studentName) throws SQLException {
        Student found = null;
        Database myDba =  new Database();
        Connection conn = myDba.getConnection();
        PreparedStatement stmt = conn.prepareCall("SELECT * FROM students WHERE name = ?");
        stmt.setString(1, studentName);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()){
            found = new Student();
            found.setName(rs.getString("name"));
            found.setAge(rs.getInt("age"));
            found.setId(rs.getLong("id"));

            }
            return found;
    }

        public void  updateStudent (Student studentName) throws SQLException {
            Database myDba = new Database();
            Connection conn = myDba.getConnection();
            PreparedStatement stmt = conn.prepareCall("UPDATE  students set name=?,age=? WHERE id =?");
            stmt.setString(1, studentName.getName());
            stmt.setInt(2, studentName.getAge());
            stmt.setLong(3, studentName.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing student was updated successfully!");
            }

        }

    public void  deleteStudent (Student studentName) throws SQLException {
        Database myDba = new Database();
        Connection conn = myDba.getConnection();
        PreparedStatement stmt = conn.prepareCall("DELETE From students WHERE id =?");
        stmt.setLong(1, studentName.getId());

        int rowsDeleted = stmt.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A student was deleted successfully!");
        }

    }

}
