package com.hzzy.mpq.studnetsocket.mapper;


import com.hzzy.mpq.studnetsocket.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author:
 * @description:
 * @date: create in
 */
@Mapper
public interface StudentMapper{

    List<Student> selectStudentList();

    Integer insertStudent(Student student);
}
