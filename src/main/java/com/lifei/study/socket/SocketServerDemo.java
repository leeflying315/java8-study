package com.lifei.study.socket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketServerDemo
{
    public static void main(String[] args)
        throws Exception
    {
        ServerSocket serverSocket = null;
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8080));
        System.out.println("socket bind in 8080");
        while (true)
        {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                try
                {
                    // 由Socket对象得到输入流，并构造相应的BufferedReader对象
                    BufferedReader is = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                    while (true)
                    {
                        System.out.println("Client:" + is.readLine());
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
