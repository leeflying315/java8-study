package com.lifei.study;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClientDemo {
    public static void main(String[] args) throws IOException {

        Socket socket = null;
        socket = new Socket("127.0.0.1", 8080);
        //向服务器端第一次发送
        OutputStream netOut = socket.getOutputStream();
        DataOutputStream doc = new DataOutputStream(netOut);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        //向服务器端第二次发送
        doc.writeUTF("save");
        String res = in.readUTF();
        System.out.println("返回的接口名称" + res);
        String data = "{name:zhangsan, age:22}";
        doc.writeUTF(data);
        res = in.readUTF();
        System.out.println("返回的数据" + res);
        doc.close();
        in.close();
        socket.close();
    }
}
