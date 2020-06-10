package com.servlet.commodity;

import com.dao.CommodityDao;
import com.dao.impl.CommodityDaoImpl;
import com.model.Commodity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ShowServlet extends HttpServlet {
    private CommodityDao commodityTool = new CommodityDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        req.getRequestDispatcher("/commodity/show.jsp").forward(req,resp);
    }
}
