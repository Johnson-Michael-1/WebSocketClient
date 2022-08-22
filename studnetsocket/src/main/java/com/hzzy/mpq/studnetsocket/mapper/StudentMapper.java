package com.hzzy.mpq.studnetsocket.mapper;


import com.hzzy.mpq.studnetsocket.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: duzhibin
 * @description:
 * @date: create in 09:57 2022/07/01
 */
@Mapper
public interface StudentMapper{

    List<Student> selectStudentList();

    Integer insertStudent(Student student);
}
