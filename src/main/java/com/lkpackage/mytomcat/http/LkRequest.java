package com.lkpackage.mytomcat.http;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

/**
 * @author UnknownCode
 * @version 1.0
 * @date 2023/5/30 23:09
 * 处理封装httpRequest
 */
public class LkRequest {
    private String method = ""; // 请求方法
    private Socket socket = null;
    private String uri = ""; // uri 比如 /cal
    private InputStream inputStream = null;
    // 存放k-v 比如 num1=10&num2=10
    public static HashMap<String, String> parameter = new HashMap<String, String>();

    public LkRequest(Socket socket) {
        this.socket = socket;
        init();
    }

    // 处理请求头
    public void init() {
        try {
            // 获取流
            inputStream = socket.getInputStream();
            String read = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            // 读取第一行
            read = br.readLine(); // GET /cal?hello=1&world=2 HTTP/1.1
            // 对字符串进行分割
            String[] strings = read.split(" ");
            method = strings[0];
            String uris = strings[1];
            // 判断uris是否有? 或者 &
            int index = 0;
            if ((index = uris.indexOf("?")) == -1){
                // 说明没有?,直接赋值
                uri = uris;
            }else{
                uri = uris.substring(0,index);
                // 判断是否有参数
                String params = uris.substring(index+1);
                String[] param = params.split("&");
                // 直接遍历
                for (String pa : param) {
                    String[] split = pa.split("=");
                    if (split.length == 2){
                        parameter.put(split[0],split[1]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 返回参数
    public String getParameter(String key){
        if (parameter.containsKey(key)){
            return parameter.get(key);
        }
        return null;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public static HashMap<String, String> getParameter() {
        return parameter;
    }

    public static void setParameter(HashMap<String, String> parameter) {
        LkRequest.parameter = parameter;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
