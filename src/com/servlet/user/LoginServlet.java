package com.servlet.user;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    private UserDao userTool = new UserDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        int loginId = userTool.loginCheck(userName, password);
        if(loginId != 0) {
            HttpSession hs = req.getSession();
            hs.setAttribute("user",userName);
            resp.getWriter().write("200");
        } else {
            resp.getWriter().write("0");
        }


    }
}
