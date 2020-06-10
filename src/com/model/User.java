package com.model;

import java.util.ArrayList;

public class User {
    private int id = 0;
    private String name = null;
    private String password = null;
    private ArrayList<Commodity> shoppingCart = null;
    private ArrayList<Commodity> goods = null;

    public User(int id,String name, String password, ArrayList<Commodity> shoppingCart, ArrayList<Commodity> goods) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.shoppingCart = shoppingCart;
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Commodity> getShoppingCart() {
        return shoppingCart;
    }

    public ArrayList<Commodity> getGoods() {
        return goods;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setShoppingCart(ArrayList<Commodity> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void setGoods(ArrayList<Commodity> goods) {
        this.goods = goods;
    }
    public String getList(String type) {
        StringBuilder list = new StringBuilder();
        list.append('[');
        if("cart".equals(type)) {
            if(this.shoppingCart.size() == 0) {
                return "[]";
            }
            for(Commodity c : this.shoppingCart) {
                list.append(c.getId() + ',');
            }
        } else {
            if(this.goods.size() == 0) {
                return "[]";
            }
            for(Commodity c : this.goods) {
                list.append(c.getId() + ',');
            }
        }

        list.deleteCharAt(list.length() - 1);
        list.append(']');
        return list.toString();

    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", shoppingCart=" + shoppingCart +
                ", goods=" + goods +
                '}';
    }
}
