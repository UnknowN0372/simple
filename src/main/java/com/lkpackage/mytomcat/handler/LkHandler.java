package com.lkpackage.mytomcat.handler;

import com.lkpackage.mytomcat.Tomcat;
import com.lkpackage.mytomcat.http.LkRequest;
import com.lkpackage.mytomcat.http.LkResponse;
import com.lkpackage.mytomcat.servlet.LkHttpServlet;
import com.lkpackage.mytomcat.utils.WebUtils;
import com.lkpackage.servlet.CalServlet;

import java.io.*;
import java.net.Socket;

/**
 * @author UnknownCode
 * @version 1.0
 * @date 2023/5/30 23:09
 * 开线程
 */
public class LkHandler implements Runnable{
    private Socket socket = null;

    public LkHandler(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        try {
            // 在这里面处理请求
            // 获取流对象
            LkRequest request = new LkRequest(socket);
            LkResponse response = new LkResponse(socket);
            // 先从容器中获取对象,这里应该用到反射
            String uri = request.getUri();
            String servletName = Tomcat.urlMap.get(uri);
            if (servletName != null && !"".equals(servletName)){
                // 说明有servletName,可以从实例化容器中查找该对象
                LkHttpServlet lkHttpServlet = Tomcat.servletMap.get(servletName);
                lkHttpServlet.service(request,response);
            }else {// 说明没有该uri,可能为静态资源
                os = response.getOutputStream();
                String body = "<h1>404 NOT FOUND</h1>";
                String resp = LkResponse.respHeader + body;
                os.write(resp.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            try {
                if (os != null){
                    os.flush();
                    os.close();
                }
                if (br != null){
                    br.close();
                }
                if (is != null){
                    is.close();
                }
                if (socket != null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
