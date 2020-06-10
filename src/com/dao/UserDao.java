package com.dao;

import com.model.Commodity;
import com.model.User;

import java.util.ArrayList;

public interface UserDao {
    public boolean createUser(User u);
    public boolean updateUser(int uid,User u);
    public ArrayList<Commodity> getShoppingCart(int uid);
    public ArrayList<Commodity> getShoppingCart(String name);
    public ArrayList<Commodity> getGoods(String name);
    public ArrayList<Commodity> getGoods(int uid);
    public boolean addGood(int cid,String name);
    public boolean addCart(int cid,String name);
    public boolean deleteGood(int cid,String name);
    public boolean deleteCart(int cid,String name);
    public int loginCheck(String name, String password);
    public int register(String name, String password);
}
