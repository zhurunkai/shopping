package com.servlet.order;

import com.dao.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

public class getOrders extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        req.getRequestDispatcher("/commodity/getOrder.jsp").forward(req,resp);
//        try {
//            String userName = req.getParameter("user");
//            JDBCUtil ju = new JDBCUtil();
//            ResultSet rs = ju.query("SELECT * FROM order WHERE user ='" + userName + "'");
//
//            resp.getWriter().write("200");
//        } catch (Exception e) {
//            e.printStackTrace();
//            resp.getWriter().write("0");
//        }
    }
}
