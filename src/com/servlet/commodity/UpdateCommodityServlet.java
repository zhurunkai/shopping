package com.servlet.commodity;

import com.dao.CommodityDao;
import com.dao.impl.CommodityDaoImpl;
import com.model.Commodity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateCommodityServlet extends HttpServlet {
    CommodityDao commodityTool = new CommodityDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String desc = req.getParameter("desc");
        String price = req.getParameter("price");
        String image = req.getParameter("image");
        Commodity c = new Commodity(Integer.parseInt(id), name, image, Double.valueOf(price), desc);
        boolean res = commodityTool.createCommodity(c);
        if(res) {
            resp.getWriter().write("200");
        } else {
            resp.getWriter().write("0");
        }
    }
}
