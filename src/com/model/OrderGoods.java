package com.model;

public class OrderGoods {
    private int id = 0;
    private int orderId = 0;
    private int commodityId = 0;
    private double price = 0;
    private int num = 0;
    private double totalPrice = 0;

    public OrderGoods(int orderId, int commodityId, double price, int num, double totalPrice) {
        this.orderId = orderId;
        this.commodityId = commodityId;
        this.price = price;
        this.num = num;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public double getPrice() {
        return price;
    }

    public int getNum() {
        return num;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
