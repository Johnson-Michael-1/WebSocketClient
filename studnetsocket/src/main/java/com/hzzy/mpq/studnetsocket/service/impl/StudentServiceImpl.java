package com.hzzy.mpq.studnetsocket.service.impl;

import com.hzzy.mpq.studnetsocket.entity.Student;
import com.hzzy.mpq.studnetsocket.mapper.StudentMapper;
import com.hzzy.mpq.studnetsocket.service.IStudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: duzhibin
 * @description:
 * @date: create in
 */
@Service
public class StudentServiceImpl implements IStudentServiceImpl {

    @Autowired
    private StudentMapper studentMapper;

    public List findAll() {
        return studentMapper.selectStudentList();
    }

    public int addStudent(Student student) {

        return studentMapper.insertStudent(student);
    }
}
