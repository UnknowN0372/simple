package com.lkpackage.mytomcat;

import com.lkpackage.mytomcat.handler.LkHandler;
import com.lkpackage.mytomcat.servlet.LkHttpServlet;
import com.lkpackage.test.TestByXml;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author UnknownCode
 * @version 1.0
 * @date 2023/5/30 23:12
 * Tomcat服务器
 */
public class Tomcat {
    // 这个map存放 k- servlet-url 和 v- servlet-name
    public static ConcurrentHashMap<String,String> urlMap = new ConcurrentHashMap<String,String>();
    // 这个map 存放 k- servlet-name 和 v- servletObject
    public static ConcurrentHashMap<String, LkHttpServlet> servletMap = new ConcurrentHashMap<String, LkHttpServlet>();

    // 可以是静态方法
    public void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器正在监听");
        while (!serverSocket.isClosed()){// 只要这个服务没有关闭,一直等待连接
            Socket socket = serverSocket.accept();
            new Thread(new LkHandler(socket)).start(); // 每次有请求就开一个线程
        }
    }

    public static void main(String[] args) throws IOException {
        Tomcat tomcat = new Tomcat();
        tomcat.init();
        tomcat.run();
    }

    /**
     * 完成xml解析,并将servlet 和 servlet-mapping 装填到两个容器中
     * 这边在初始化容器时就反射装填了,可以在请求时再反射装填 LkhttpServlet 对象
     * 这个初始化可以是静态方法
     */
    public void init(){
        String path = Tomcat.class.getResource("/").getPath();
        String file = "web.xml";
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(path + file);
            Element root = document.getRootElement();
            List<Element> servlets = root.elements("servlet");
            List<Element> mappings = root.elements("servlet-mapping");
            for (Element servlet : servlets) {
                Element name = servlet.element("servlet-name");
                Element servletClass = servlet.element("servlet-class");
                // 这里运行类型是CalServlet,使用了动态绑定机制
                servletMap.put(name.getText(),(LkHttpServlet) Class.forName(servletClass.getText()).newInstance());
            }
            for (Element mapping : mappings) {
                Element name = mapping.element("servlet-name");
                Element url = mapping.element("url-pattern");
                urlMap.put(url.getText(),name.getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
