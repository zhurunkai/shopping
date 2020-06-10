package com.servlet.order;

import com.dao.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class createOrderGood extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        try {
            String orderId = req.getParameter("orderId");
            String commodityId = req.getParameter("commodityId");
            double price = Double.parseDouble(req.getParameter("price"));
            int num = Integer.parseInt(req.getParameter("num"));
            double totalPrice = Double.parseDouble(req.getParameter("totalPrice"));
            JDBCUtil ju = new JDBCUtil();
            ju.cud("INSERT INTO order_goods (order_id,commodity_id,price,num,totalprice) VALUES ('" + orderId + "','" + commodityId + "'," + price + "," + num + "," + totalPrice + ")");
            resp.getWriter().write("200");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("0");
        }
    }
}
