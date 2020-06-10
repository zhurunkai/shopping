<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.Commodity" %>
<%@ page import="com.dao.CommodityDao" %>
<%@ page import="com.dao.impl.CommodityDaoImpl" %>
<%@ page import="com.model.User" %>
<%@ page import="com.dao.UserDao" %>
<%@ page import="com.dao.impl.UserDaoImpl" %>
<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%
    UserDao userTool = new UserDaoImpl();
    ArrayList<Commodity> carts = userTool.getShoppingCart((String) session.getAttribute("user"));
    ArrayList<Integer> nums = new ArrayList<>();
    for (Commodity cart : carts) {
        nums.add(1);
    }

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>乐购-购物车</title>
    <link rel="stylesheet" href="style.css"/>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
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
            <div class="title-text">乐购——购物车。</div>
        </div>
        <div class="line"></div>
        <div class="coms-container">

            <%
                for (int i = 0; i < carts.size(); i++) {


            %>
            <div class="com-box">
                <img
                        class="com-img"
                        src="<%=carts.get(i).getImage()%>"
                        alt=""
                />

                <div class="text-container">
                    <div  style="display: none"><%=carts.get(i).getId()%></div>
                    <div class="com-title"><%=carts.get(i).getName()%>
                    </div>
                    <div class="price-add">
                        <div class="com-price">￥<%=carts.get(i).getPrice()%>
                        </div>

                        <img class="add-cart-icon" data-cid="<%=carts.get(i).getId()%>" onclick="deleteCart(event)"
                             src="https://kairz-1258976754.cos.ap-shanghai.myqcloud.com/delete.png" alt="">


                    </div>

                    <div style="margin-bottom: 20px;padding-left: 10px">数量<input type="number" class="nums" data-cid="<%=carts.get(i).getId()%>" value="<%=nums.get(i)%>" />
                    </div>
                </div>
            </div>
            <%
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
    <div class="cart-box"  onclick="pay()" data-toggle="modal" data-target="#exampleModal">
        <img class="cart-img" src="https://kairz-1258976754.cos.ap-shanghai.myqcloud.com/order.png" alt="">
    </div>
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">生成订单</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="order-content">
                    ...
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" onclick="addOrder()">确定</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    var total = 0;
    var prices = [];
    <%
        for (Commodity cart : carts) {
            %>
    prices.push(<%=cart.getPrice()%>);
    <%
        }
    %>
    function pay() {
        $('.nums').each(function(index,element) {
            total += element.valueAsNumber * prices[index];
        });
        $('#order-content').html("您需要支付"+total+"元人民币，是否生成订单？");


    }
    function addOrder() {
        var orderItems = '';
        $('.nums').each(function (index,element) {
            orderItems = orderItems + element.dataset.cid + ',' + prices[index] + ',' + element.valueAsNumber + ',' + element.valueAsNumber * prices[index] + '&';
            total += element.valueAsNumber * prices[index];
        });
        orderItems=orderItems.substring(0,orderItems.length-1);
        var ts = new Date().getTime();
        $.ajax({
            method: 'POST',
            url: 'http://localhost:8080/shopping/order/createorder',
            data: {
                orderItems:orderItems,
                totalPrice:total,
                createTime:ts,
                user: '<%=request.getSession().getAttribute("user")%>'
            },
            success: function (res) {
                console.log(res)
                if (res == '200') {
                    alert('生成订单成功')
                    window.location.href =
                        'http://localhost:8080/shopping/commodity/getOrder.jsp'
                } else {
                    alert('生成订单失败')
                }
            },
            error: function (err) {
                console.log(err)
                alert('生成订单失败')
            }
        })
    }
    function deleteCart(e) {
        var cid = e.target.dataset.cid
        $.ajax({
            method: 'POST',
            url: 'http://localhost:8080/shopping/commodity/deletecart',
            data: {
                cid: cid
            },
            success: function (res) {
                console.log(res)
                if (res == '200') {
                    alert('移除成功')
                    location.reload();
                } else {
                    alert('移除失败')
                }
            },
            error: function (err) {
                console.log(err)
                alert('移除失败')
            },
        })
    }
</script>
</html>
