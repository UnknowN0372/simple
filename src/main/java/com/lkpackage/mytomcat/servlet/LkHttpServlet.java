package com.lkpackage.mytomcat.servlet;

import com.lkpackage.mytomcat.http.LkRequest;
import com.lkpackage.mytomcat.http.LkResponse;

import java.io.IOException;

/**
 * @author UnknownCode
 * @version 1.0
 * @date 2023/5/30 23:12
 */
public abstract class LkHttpServlet implements LkServlet{
    public void init() throws Exception {

    }

    public void service(LkRequest req, LkResponse resp) throws IOException {
        String method = req.getMethod();
        if (method.equalsIgnoreCase("GET")){
            this.doGet(req, resp);
        }else if (method.equalsIgnoreCase("POST")){
            this.doPost(req, resp);
        }
    }

    public abstract void doGet(LkRequest req, LkResponse resp);

    public abstract void doPost(LkRequest req, LkResponse esp);

    public void destroy() {

    }
}
