package com.hzzy.mpq.studnetsocket.controller;

import com.hzzy.mpq.studnetsocket.entity.Student;
import com.hzzy.mpq.studnetsocket.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author:
 * @description:
 * @date:
 */
@RestController
@CrossOrigin
@RequestMapping("/system/sstudent")
public class TestController {


//    ServerSocket serverSocket = new ServerSocket(8089);
//        System.out.println("服务端启动");
//        while (true){
//        Socket socket = serverSocket.accept();
//    }

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping
    public List selectStudent(){
        List studentList = studentService.findAll();
        System.out.println("未使用ip======");
        return studentList;
    }

    @PostMapping
    public int addStudent(@Validated @RequestBody Student student){
        int i = studentService.addStudent(student);
        return i;
    }
}
