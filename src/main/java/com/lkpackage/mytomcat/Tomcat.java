package com.lkpackage.mytomcat;

import com.lkpackage.mytomcat.handler.LkHandler;
import com.lkpackage.mytomcat.servlet.LkHttpServlet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author UnknownCode
 * @version 1.0
 * @date 2023/5/30 23:12
 * Tomcat服务器
 */
public class Tomcat {
    // 这个map存放 k- servlet-url 和 v- servlet-name
    private static ConcurrentHashMap<String,String> urlMap = new ConcurrentHashMap<String,String>();
    // 这个map 存放 k- servlet-name 和 v- servletObject
    private static ConcurrentHashMap<String, LkHttpServlet> servletMap = new ConcurrentHashMap<String, LkHttpServlet>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器正在监听");
        while (!serverSocket.isClosed()){// 只要这个服务没有关闭,一直等待连接
            Socket socket = serverSocket.accept();
            new Thread(new LkHandler(socket)).start(); // 每次有请求就开一个线程
        }
    }
}
