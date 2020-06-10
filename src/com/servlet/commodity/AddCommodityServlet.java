package com.servlet.commodity;

import com.dao.CommodityDao;
import com.dao.JDBCUtil;
import com.dao.UserDao;
import com.dao.impl.CommodityDaoImpl;
import com.dao.impl.UserDaoImpl;
import com.model.Commodity;
import com.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddCommodityServlet extends HttpServlet {
    private CommodityDao commodityTool = new CommodityDaoImpl();
    private UserDao userTool = new UserDaoImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        String price = req.getParameter("price");
        String image = req.getParameter("image");
        Commodity c = new Commodity(0, name, image, Double.valueOf(price), desc);
        boolean res = commodityTool.createCommodity(c);
        ResultSet rs = new JDBCUtil().query("SELECT * FROM commodity");


        int cid = 0;
        try {
            ArrayList<Commodity> coms = new ArrayList<>();
            while (rs.next()) {
                coms.add(new Commodity(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getDouble("price"),
                        rs.getString("desc")));
            }
            cid = coms.get(coms.size() - 1).getId();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (cid != 0) {
            HttpSession hs = req.getSession();
            if (hs.getAttribute("user") == null) {
                resp.sendRedirect("/shopping/user/login.jsp");
            }
            userTool.addGood(cid, (String) hs.getAttribute("user"));
        }

        resp.getWriter().write(res ? "200" : "0");
    }
}
