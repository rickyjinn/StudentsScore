package com.company.dao.impl;

import com.company.dao.IStudentDao;
import com.company.entity.Student;
import com.company.util.JdbcUtils;

import java.sql.PreparedStatement;

public class StudentDao implements IStudentDao {

    public PreparedStatement pstmt;

    @Override
    public void addStudent(Student student) throws Exception {
        String sql = "insert into Student (id,name,food) values (?,?,?);";

        pstmt = JdbcUtils.getPreparedStatement(sql);

        pstmt.setInt(1, student.getId());
        pstmt.setString(2, student.getName());
        pstmt.setString(3, student.getFood());

        pstmt.executeUpdate();
//            JdbcUtils.closePstmt(pstmt)

    }

    @Override
    public void updateStudent(Student student) throws Exception {
        int id = student.getId();
        String name = student.getName();
        String food = student.getFood();
        String sql = "UPDATE Student SET name=?,food=? WHERE id = ? ";

        pstmt = JdbcUtils.getPreparedStatement(sql);

        pstmt.setString(1, student.getName());
        pstmt.setString(2, student.getFood());
        pstmt.setInt(3, student.getId());

        pstmt.executeUpdate();

        throw new Exception();

    }
}
