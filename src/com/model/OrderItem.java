package com.model;

import java.util.ArrayList;

public class OrderItem {
    public int id = 0;
    public double totalPrice = 0;
    public ArrayList<Item> items = null;

    public OrderItem(int id,double totalPrice, ArrayList<Item> items) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
