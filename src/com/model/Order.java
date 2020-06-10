package com.model;

public class Order {
    private int id = 0;
    private double totalPrice = 0;
    private int state = 0;
    private String createTime = null;
    private String payTime = null;

    public Order(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getState() {
        return state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
}
