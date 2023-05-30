package com.lkpackage.mytomcat.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author UnknownCode
 * @version 1.0
 * @date 2023/5/30 23:09
 */
public class LkResponse {
    private Socket socket = null;
    private OutputStream outputStream = null;
    public static String respHeader = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/html; charset=utf-8\r\n\r\n";

    public LkResponse(Socket socket) {
        this.socket = socket;
        try {
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public static String getRespHeader() {
        return respHeader;
    }

    public static void setRespHeader(String respHeader) {
        LkResponse.respHeader = respHeader;
    }
}
