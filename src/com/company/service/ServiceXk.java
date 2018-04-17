package com.company.service;

import com.company.dao.impl.StudentDao;
import com.company.entity.Student;
import com.company.util.JdbcUtils;
import com.company.util.TransactionManager;

public class ServiceXk {

    private StudentDao stuDao = new StudentDao();


    public void testDaos() throws Exception {

        Student student1 = new Student(12,"wangsan","樱桃");
        Student student2 =  new Student(12,"wangsan","cherry");

        TransactionManager tx = JdbcUtils.getTranManager();
        try{
            tx.beginTransaction();

            stuDao.addStudent(student1);
            //stuDao.addStudent(student2);
            stuDao.updateStudent(student2);

            tx.commitAndClose(null,stuDao.pstmt);
        }catch (Exception e) {

            tx.rollbackAndClose(null,stuDao.pstmt);
            System.out.println("rollback");

        }
    }
}
