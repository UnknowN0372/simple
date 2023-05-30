package com.lkpackage.mytomcat.servlet;

import com.lkpackage.mytomcat.http.LkRequest;
import com.lkpackage.mytomcat.http.LkResponse;

import java.io.IOException;

/**
 * @author UnknownCode
 * @version 1.0
 * @date 2023/5/30 23:12
 */
public interface LkServlet {
    void init() throws Exception;

    void service(LkRequest req, LkResponse resp) throws IOException;

    void destroy();
}
