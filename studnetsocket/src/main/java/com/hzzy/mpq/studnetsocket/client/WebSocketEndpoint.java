package com.hzzy.mpq.studnetsocket.client;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hzzy.mpq.studnetsocket.entity.Student;
import com.hzzy.mpq.studnetsocket.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author:
 * @description:
 * @date: create in 
 */
@ServerEndpoint("/webSocket/{id}")
@Component
public class WebSocketEndpoint{
    private static StudentServiceImpl studentService;

    @Autowired
    public void setStudentService (StudentServiceImpl studentService){
        WebSocketEndpoint.studentService = studentService;
    }


    static Log log = LogFactory.get(WebSocketEndpoint.class);
    private Session session;
    //与特定id的WebSocket服务器连接
    private String id = "";
    private static ConcurrentHashMap<String,Session> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 建立连接后回调的函数
     * @param id
     * @param session
     */
    @OnOpen
    public void open(@PathParam("id") String id, Session session){

        this.session = session;
        this.id = id;
        webSocketMap.put(id, session);
        log.info("***************建立连接：" + id);
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void close(){
        webSocketMap.remove(id);
        log.info("***************关闭连接："+id);
    }

    /**
     *接收服务器发送的消息
     *      * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        if(StringUtils.isNotBlank(message)){
            try {
                // 打印浏览器传输过来的学生信息
                System.out.println("WebSocket接收浏览器传输过来的学生信息 = " + message);

                // 将客户端传送过来的学生信息数据插入到新创建的数据库表socket_student中
                Student student = JSON.parseObject(message, Student.class);
                int affectedLineOfSql = studentService.addStudent(student);
                System.out.println("数据插入成功，影响到的数据库插入行数 = " + affectedLineOfSql);

                // 查询新创建的数据库表socket_student中的所有学生信息
                List<Student> studentList = studentService.findAll();
                for (Student eachStudent : studentList) {
                    System.out.println("student = " + eachStudent); // 每一条
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 服务器主动推送信息
     */
    public static void sendMsg(String toId,String msg){
        Session session = WebSocketEndpoint.webSocketMap.get(toId);
        try {
            session.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
