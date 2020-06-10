package com.model;

public class Item {
    public int num = 0;
    public int commodityId = 0;
    public String name = null;
    public double price = 0;
    public double totalPrice = 0;

    public Item(int commodityId) {
        this.commodityId = commodityId;
    }

    public Item(int num, int commodityId, String name, double price, double totalPrice) {
        this.num = num;
        this.commodityId = commodityId;
        this.name = name;
        this.price = price;
        this.totalPrice = totalPrice;
    }
}
