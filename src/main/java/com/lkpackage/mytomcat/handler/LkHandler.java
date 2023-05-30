package com.lkpackage.mytomcat.handler;

import com.lkpackage.mytomcat.http.LkRequest;
import com.lkpackage.mytomcat.http.LkResponse;
import com.lkpackage.mytomcat.utils.WebUtils;
import com.lkpackage.servlet.CalServlet;

import java.io.*;
import java.net.Socket;

/**
 * @author UnknownCode
 * @version 1.0
 * @date 2023/5/30 23:09
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
            CalServlet calServlet = new CalServlet();
            calServlet.service(request,response);
//            socket.close();
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
