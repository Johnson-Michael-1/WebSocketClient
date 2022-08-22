package com.hzzy.mpq.studnetsocket.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
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

//    @Autowired
//    private StudentServiceImpl studentService;
//
//    private static WebSocketEndpoint webSocketEndpoint;
//    @PostConstruct
//    public void init() {
//        System.out.println("------>init");
//        webSocketEndpoint = this;
//        webSocketEndpoint.studentService = this.studentService;
//    }

    private static StudentServiceImpl studentService;

    @Autowired
    public void setStudentService (StudentServiceImpl studentService){
        WebSocketEndpoint.studentService= studentService;
    }


    static Log log = LogFactory.get(WebSocketEndpoint.class);
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //当前用户id
    private String id="";
    private static ConcurrentHashMap<String,Session> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 连接建立
     * @param id
     * @param session
     */
    @OnOpen
    public void open(@PathParam("id") String id, Session session){

        this.session = session;
        this.id=id;
        webSocketMap.put(id,session);
        log.info("========>建立连接"+id);

    }

    /**
     * 连接关闭
     */
    @OnClose
    public void close(){
        webSocketMap.remove(id);
        log.info("=========》关闭连接"+id);
    }

    /**
     *接受客户端发送的消息，并给回复
     *      * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        /*
        message为json格式字符串
       例： {"text":"这是个例子", "toId":"001"}
         */
        if(StringUtils.isNotBlank(message)){
            try {

                //查询学生信息
                List studentList = studentService.findAll();
                System.out.println("studentList：" + studentList);
                //将查到的学生信息转换为JSON对象传输
                Object toJsonObject = JSON.toJSON(studentList);


                //转化为object,解析发送的报文
//              JSONObject jsonObject = JSON.parseObject(message);
                com.alibaba.fastjson.JSONObject   jsonObject = com.alibaba.fastjson.JSON.parseObject(message);


                //得到接受者id
                String toId=jsonObject.getString("toId");
                System.out.println("jsonObject.getString(\"toId\")：" + jsonObject.getString("toId"));
                System.out.println("jsonObject.toJSONString()："+jsonObject.toJSONString());


                System.out.println("toJsonObject.toString()："+toJsonObject.toString());


                //在发送的报文中加入发送人id
                //{"text":"这是个例子", "toId":"001", "fromId":"002"}
//                jsonObject.put("fromId",this.id);

                webSocketMap.get(toId).getBasicRemote().sendText(toJsonObject.toString());


//                //发送给toId对应的用户
//                if(StringUtils.isNotBlank(toId)&&webSocketMap.containsKey(toId)){
//                    webSocketMap.get(toId).getBasicRemote().sendText(jsonObject.toJSONString());
//                }else{
//                    log.info("信息接收者"+toId+"不在服务器上");
//                    //该id用户不在服务器上，可选择存储在redis中，如果不做此操作就只能两人同时在线才可以互相接收到信息
//                }
            }catch (Exception e){
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
