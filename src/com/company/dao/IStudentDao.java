package com.company.dao;

import com.company.entity.Student;

public interface IStudentDao {

    void addStudent(Student student)throws Exception;

    void updateStudent(Student student)throws Exception;

}
