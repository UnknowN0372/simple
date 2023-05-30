package com.lkpackage.servlet;

import com.lkpackage.mytomcat.http.LkRequest;
import com.lkpackage.mytomcat.http.LkResponse;
import com.lkpackage.mytomcat.servlet.LkHttpServlet;
import com.lkpackage.mytomcat.utils.WebUtils;

import java.io.IOException;

/**
 * @author UnknownCode
 * @version 1.0
 * @date 2023/5/31 0:50
 */
public class CalServlet extends LkHttpServlet {

    public void doGet(LkRequest req, LkResponse resp) {
        doPost(req, resp);
    }

    public void doPost(LkRequest req, LkResponse resp) {
        int num1 = WebUtils.parseInt(req.getParameter("num1"), 0);
        int num2 = WebUtils.parseInt(req.getParameter("num2"), 0);
        String body = "<h1>"+num1+" + "+num2+" = "+(num1+num2)+"</h1>";
        String res = LkResponse.respHeader+body;
        try {
            resp.getOutputStream().write(res.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                req.getSocket().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
