package com.servlet.user;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
    private UserDao userTool = new UserDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        int registerId = userTool.register(userName,password);
        if(registerId == 1) {
            resp.getWriter().write("200");
        } else {
            resp.getWriter().write("0");
        }

    }
}
