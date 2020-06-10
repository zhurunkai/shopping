package com.servlet.commodity;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCartServlet extends HttpServlet {
    UserDao userTool = new UserDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String rawCid = req.getParameter("cid");
        String username = (String)req.getSession().getAttribute("user");
        boolean res = userTool.deleteCart(Integer.parseInt(rawCid),username);
        if(res) {
            resp.getWriter().write("200");
        } else {
            resp.getWriter().write("0");
        }
    }
}
