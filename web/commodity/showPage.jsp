<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.Commodity" %>
<%@ page import="com.dao.CommodityDao" %>
<%@ page import="com.dao.impl.CommodityDaoImpl" %>
<%@ page import="com.dao.UserDao" %>
<%@ page import="com.dao.impl.UserDaoImpl" %>
<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%
    String pageNum = request.getParameter("page");
    CommodityDao comTool = new CommodityDaoImpl();
    ArrayList<Commodity> rawComList = comTool.commodityList();
    ArrayList<Commodity> comList = new ArrayList<>();
    int pages = rawComList.size() / 8 + 1;
    for (int i = 8*(Integer.parseInt(pageNum)-1); i < rawComList.size() && i < 8*Integer.parseInt(pageNum); i++) {
        comList.add(rawComList.get(i));
    }
    UserDao userTool = new UserDaoImpl();
    ArrayList<Commodity> carts = userTool.getShoppingCart((String) session.getAttribute("user"));


%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>乐购-商品列表</title>
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
            <div class="title-text">乐购，让消费更美好。</div>
        </div>
        <div class="line"></div>
        <div class="coms-container">

            <%
                for (Commodity commodity : comList) {
            %>
            <div class="com-box">
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
                        <%
                            if (carts == null) {

                        %>
                        <img class="add-cart-icon" data-cid="<%=commodity.getId()%>" onclick="addCart(event)"
                             src="https://kairz-1258976754.cos.ap-shanghai.myqcloud.com/addCart.png" alt="">
                        <%
                        } else {
                            boolean ifshow = true;
                            for (Commodity cart : carts) {
                                if (cart.getId() == commodity.getId()) {
                                    ifshow = false;
                                }
                            }
                            if (ifshow) {
                        %>
                        <img class="add-cart-icon" data-cid="<%=commodity.getId()%>" onclick="addCart(event)"
                             src="https://kairz-1258976754.cos.ap-shanghai.myqcloud.com/addCart.png" alt="">
                        <%
                                }

                            }

                        %>

                    </div>

                    <div class="desc"><%=commodity.getDesc()%>
                    </div>
                </div>
            </div>
            <%
                }
            %>


        </div>
        <nav class="pagi">
            <ul class="pagination pagination-sm">
                <li class="page-item">
                    <a href="show.jsp">
                <span class="page-link">
                  1
                </span>
                    </a>
                </li>
                <%

                        for (int i = 2; i <= pages; i++) {
                            if(Integer.parseInt(pageNum) == i) {
                                %>
                <li class="page-item active" aria-current="page">
                <span class="page-link">
                  <%=i%>
                </span>
                </li>
                <span class="sr-only">(current)</span>

                <%
                            } else {


                %>
                <li class="page-item"><a class="page-link" href="showPage.jsp?page=<%=i%>"><%=i%></a></li>
                <%
                        }
                        }

                %>


            </ul>
        </nav>
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
<script>
    function addCart(e) {
        cid = e.target.dataset.cid
        $.ajax({
            method: 'POST',
            url: 'http://localhost:8080/shopping/commodity/addcart',
            data: {
                cid: cid
            },
            success: function (res) {
                console.log(res)
                if (res == '200') {
                    alert('添加成功')
                    location.reload();
                } else {
                    alert('添加失败')
                }
            },
            error: function (err) {
                console.log(err)
                alert('添加失败')
            },
        })
    }
</script>
</html>
