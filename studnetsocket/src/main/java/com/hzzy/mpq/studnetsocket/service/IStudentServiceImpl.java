package com.hzzy.mpq.studnetsocket.service;

import com.hzzy.mpq.studnetsocket.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: duzhibin
 * @description:
 * @date: create in 10:11 2022/07/01
 */
public interface IStudentServiceImpl {
    List findAll();

    int addStudent(Student student);
}
