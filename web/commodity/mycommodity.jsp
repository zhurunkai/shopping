<%@ page import="com.dao.UserDao" %>
<%@ page import="com.dao.impl.UserDaoImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.Commodity" %>
<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%
    UserDao userTool = new UserDaoImpl();
    ArrayList<Commodity> mine = userTool.getGoods((String) request.getSession().getAttribute("user"));
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>乐购-我的商品</title>
    <link rel="stylesheet" href="style.css"/>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <link
            href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.css"
            rel="stylesheet"
    />
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.5.0/js/bootstrap.js"></script>
</head>
<body>
<div class="page-container">
    <div class="window-box">
        <div class="logo-line">
            <img
                    class="logo-img"
                    src="https://kairz-1258976754.cos.ap-shanghai.myqcloud.com/logo.png"
                    alt=""
            />
            <div class="title-text">我发布的商品——点击商品可进行编辑</div>
        </div>
        <div class="line"></div>
        <div class="coms-container">
            <%
                if (mine.size() != 0) {
                    for (Commodity commodity : mine) {
            %>
            <div class="com-box"><a href="revise.jsp?cid=<%=commodity.getId()%>">
                <img
                        class="com-img"
                        src="<%=commodity.getImage()%>"
                        alt=""
                />

                <div class="text-container">

                    <div class="com-title"><%=commodity.getName()%>
                    </div>
                    <div class="price-add">
                        <div class="com-price">￥<%=commodity.getPrice()%>
                        </div>

                    </div>

                    <div class="desc"><%=commodity.getDesc()%>
                    </div>
                </div>
            </a>
            </div>


            <%


                    }
                }
            %>


        </div>
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
