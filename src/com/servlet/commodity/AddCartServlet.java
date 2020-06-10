package com.servlet.commodity;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCartServlet extends HttpServlet {
    private UserDao userTool = new UserDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rawCid = req.getParameter("cid");
        String username = (String)req.getSession().getAttribute("user");
        int cid = Integer.parseInt(rawCid);
        boolean res = userTool.addCart(cid,username);
        if (res) {
            resp.getWriter().write("200");
        } else {
            resp.getWriter().write("0");
        }
    }
}
