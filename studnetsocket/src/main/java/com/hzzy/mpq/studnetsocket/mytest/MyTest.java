package com.hzzy.mpq.studnetsocket.mytest;

import java.net.URI;
import java.net.URISyntaxException;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.URI;

public class MyTest{

    public static void main(String[] arg0){
        // 此处的WebSocket服务端URI，上面服务端第2点有详细说明
        MyWebSocketClient myClient = null;
        try {
            myClient = new MyWebSocketClient(new URI("ws://192.168.0.35:10001"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        // 往websocket服务端发送数据
        myClient.send("此为要发送的数据内容");
    }

}