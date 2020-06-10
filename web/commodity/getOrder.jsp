<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.Commodity" %>
<%@ page import="com.dao.CommodityDao" %>
<%@ page import="com.dao.impl.CommodityDaoImpl" %>
<%@ page import="com.model.User" %>
<%@ page import="com.dao.UserDao" %>
<%@ page import="com.dao.impl.UserDaoImpl" %>
<%@ page import="com.dao.JDBCUtil" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.model.OrderItem" %>
<%@ page import="com.model.Item" %>
<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%
    String userName = (String) request.getSession().getAttribute("user");
    JDBCUtil ju = new JDBCUtil();
    CommodityDao comTool = new CommodityDaoImpl();
    ResultSet rs = ju.query("SELECT * FROM `order` WHERE user ='" + userName + "'");
    ArrayList<OrderItem> ors = new ArrayList<>();
    try {
        while (rs.next()) {
            ResultSet rss = ju.query("SELECT * FROM order_goods WHERE order_id =" + rs.getInt("id"));
            ArrayList<Item> item = new ArrayList<>();
            while (rss.next()) {
                item.add(new Item(rss.getInt("num"), rss.getInt("commodity_id"), comTool.getName(rss.getInt("commodity_id")), rss.getDouble("price"), rss.getDouble("totalPrice")));
            }
            ors.add(new OrderItem(rs.getInt("id"),rs.getDouble("totalprice"), item));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>乐购-订单列表</title>
    <link rel="stylesheet" href="style.css"/>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <link
            href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.css"
            rel="stylesheet"
    />
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.5.0/js/bootstrap.js"></script>
</head>
<body>
<div class="page-container" id="pageContainer">
    <div class="window-box">
        <div class="logo-line">
            <img
                    class="logo-img"
                    src="https://kairz-1258976754.cos.ap-shanghai.myqcloud.com/logo.png"
                    alt=""
            />
            <div class="title-text">乐购——订单列表。</div>
        </div>
        <div class="line"></div>
        <%
            for (int i = ors.size()-1; i>=0; i--) {



        %>
        <div class="order-item" style="box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2), 0 0 6px rgba(0, 0, 0, 0.2);margin:20px;padding: 10px">
            <div style="display: flex">
            <div class="order-title" style="width: 150px">
                订单号: <%=ors.get(i).id%>
            </div>
            <div style="width: 200px">
                总价: <%=ors.get(i).totalPrice%>
            </div>
            </div>
            <table class="table">
                <tr>
                    <th>商品名称</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>总价</th>
                </tr>
                <%
                    for (Item item : ors.get(i).items) {
                %>

                <tr>
                    <td><%=item.name%></td>
                    <td><%=item.price%></td>
                    <td><%=item.num%></td>
                    <td><%=item.totalPrice%></td>
                </tr>
                <%
                    }
                %>

            </table>
        </div>
        <%
            }
        %>


    </div>
    <div class="order-box">
        <a href="getOrder.jsp">
            <img class="cart-img" src="https://kairz-1258976754.cos.ap-shanghai.myqcloud.com/orderitem.png" alt="">
        </a>
    </div>
    <div class="add-box">
        <a href="addCommodity.jsp">
            <img class="cart-img" src="https://kairz-1258976754.cos.ap-shanghai.myqcloud.com/add.png" alt="">
        </a>
    </div>

    <div class="mine-box">
        <a href="mycommodity.jsp">
            <img class="cart-img" src="https://kairz-1258976754.cos.ap-shanghai.myqcloud.com/mine.png" alt="">
        </a>
    </div>
    <div class="cart-box">
        <a href="cart.jsp">
            <img class="cart-img" src="https://kairz-1258976754.cos.ap-shanghai.myqcloud.com/cart.png" alt="">
        </a>
    </div>
</div>
</body>
</html>
