package com.servlet.order;

import com.dao.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class createOrder extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        try {
            double totalPrice = Double.parseDouble(req.getParameter("totalPrice"));
            JDBCUtil ju = new JDBCUtil();
            String createTime = req.getParameter("createTime");
            String user = req.getParameter("user");
            String orderItems = req.getParameter("orderItems");
            ju.cud("INSERT INTO `order` (totalprice,user,state,createtime,paytime) VALUES (" + totalPrice + ",'" + user + "',0,'" + createTime + "',0" + ")");
            ResultSet rs = ju.query("SELECT * FROM `order` ORDER BY id DESC LIMIT 1");
            rs.next();
            int orderId = rs.getInt("id");
            String[] order = orderItems.split("&");
            ArrayList<String[]> orderHome = new ArrayList<>();
            for (String s : order) {
                String[] v = s.split(",");
                orderHome.add(v);
            }
            for (String[] strings : orderHome) {
                ju.cud("INSERT INTO order_goods (order_id,commodity_id,price,num,totalprice) VALUES (" + orderId + "," + strings[0] + "," + strings[1] + "," + strings[2] + "," + strings[3] + ")");
            }
            resp.getWriter().write("200");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("0");
        }
    }
}
