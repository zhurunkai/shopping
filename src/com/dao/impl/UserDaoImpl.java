package com.dao.impl;

import com.dao.JDBCUtil;
import com.dao.UserDao;
import com.model.Commodity;
import com.model.User;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    private JDBCUtil ju = new JDBCUtil();

    @Override
    public boolean createUser(User u) {
        try {
            String sql = "INSERT INTO user (`name`, `password`, `shoppingCart`, `goods`) VALUES ('" + u.getName() + "','" +
                    u.getPassword() + "','" + u.getList("cart") + "','" + u.getList("goods") + "')";
            this.ju.cud(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(int uid, User u) {
        try {
            String sql = "UPDATE user u SET u.name = '" + u.getName()+ "', u.password = '" + u.getPassword() +
                    "', u.shoppingCart = '" + u.getList("cart") + "', u.goods = '" + u.getList("goods")
                    + "' WHERE u.id = " + uid;
            this.ju.cud(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Commodity> getShoppingCart(int uid) {
        try {
            String sql = "SELECT * FROM user WHERE id = " + uid + "";
            ResultSet rs = this.ju.query(sql);
            String cart = null;
            while(rs.next()) {
                cart = rs.getString("shoppingCart");
            }
            StringBuilder cartSb = new StringBuilder(cart);
            cartSb.delete(0, 1);
            cartSb.deleteCharAt(cartSb.length() - 1);
            if ("".equals(cartSb.toString())) {
                return new ArrayList<Commodity>();
            }
            String[] carts = cartSb.toString().split(",");
            ArrayList<Commodity> cartList = new ArrayList<>();
            for (String c : carts) {
                String comSql = "SELECT * FROM commodity WHERE id = " + c;
                ResultSet comRs = this.ju.query(comSql);
                cartList.add(new Commodity(comRs.getInt("id"),
                        comRs.getString("name"),
                        comRs.getString("image"),
                        comRs.getDouble("price"),
                        comRs.getString("desc")));
            }
            return cartList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Commodity> getGoods(int uid) {
        try {
            String sql = "SELECT * FROM user WHERE id = " + uid + "";
            ResultSet rs = this.ju.query(sql);
            String goods = null;
            while(rs.next()) {
                goods = rs.getString("goods");
            }
            StringBuilder goodsSb = new StringBuilder(goods);
            goodsSb.delete(0, 1);
            goodsSb.deleteCharAt(goodsSb.length() - 1);
            if ("".equals(goodsSb.toString())) {
                return new ArrayList<Commodity>();
            }
            String[] goodsStr = goodsSb.toString().split(",");
            ArrayList<Commodity> goodsList = new ArrayList<>();
            for (String c : goodsStr) {
                String comSql = "SELECT * FROM commodity WHERE id = " + c;
                ResultSet comRs = this.ju.query(comSql);
                goodsList.add(new Commodity(comRs.getInt("id"),
                        comRs.getString("name"),
                        comRs.getString("image"),
                        comRs.getDouble("price"),
                        comRs.getString("desc")));
            }
            return goodsList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public boolean addGood(int cid, String name) {
        try {
            String sql = "SELECT * FROM user WHERE name = '" + name + "'";
            ResultSet rs = this.ju.query(sql);
            rs.next();
            String good = rs.getString("goods");
            StringBuilder goodSb = new StringBuilder(good);
            goodSb.deleteCharAt(goodSb.length() - 1);
            if(goodSb.length() == 1) {
                goodSb.append("\"" + cid + "\"]");
            } else {
                goodSb.append(",\"" + cid + "\"]");
            }
            String updateSql = "UPDATE user u SET u.goods = '" + goodSb.toString() + "' WHERE name = '" + name + "'";
            this.ju.cud(updateSql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addCart(int cid, String name) {
        try {
            String sql = "SELECT * FROM user WHERE name = '" + name + "'";
            ResultSet rs = this.ju.query(sql);
            rs.next();
            String cart = rs.getString("shoppingCart");
            StringBuilder cartSb = new StringBuilder(cart);
            cartSb.deleteCharAt(cartSb.length() - 1);
            if(cartSb.length() == 1) {
                cartSb.append("\"").append(cid).append("\"]");
            } else {
                cartSb.append(",\"").append(cid).append("\"]");
            }
            String updateSql = "UPDATE user u SET u.shoppingCart = '" + cartSb.toString() + "' WHERE name = '" + name + "'";
            this.ju.cud(updateSql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteGood(int cid, String name) {
        try {
            int cidLen = (cid + "").length() + 3;
            String sql = "SELECT * FROM user WHERE id = '" + name + "'";
            ResultSet rs = this.ju.query(sql);
            rs.next();
            String goods = rs.getString("goods");
            StringBuilder goodsSb = new StringBuilder(goods);
            int startIndex = goods.indexOf("\"" + cid + "\"");
            if(startIndex + 2+ (cid+"").length() == goods.length()-1) {
                goodsSb.delete(startIndex-1, startIndex + cidLen);
            }else {
                goodsSb.delete(startIndex, startIndex + cidLen);
            }
            String updateSql = "UPDATE user u SET u.goods = '" + goodsSb.toString() + "' WHERE id = '" + name + "'";
            this.ju.cud(updateSql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCart(int cid, String name) {
        try {
            int cidLen = (cid + "").length() + 3;
            String sql = "SELECT * FROM user WHERE name = '" + name + "'";
            ResultSet rs = this.ju.query(sql);
            rs.next();
            String cart = rs.getString("shoppingCart");
            StringBuilder cartSb = new StringBuilder(cart);
            int startIndex = cart.indexOf("\"" + cid + "\"");
            if(startIndex + 2+ (cid+"").length() == cart.length()-1) {
                cartSb.delete(startIndex-1, startIndex + cidLen);
            } else {
                cartSb.delete(startIndex, startIndex + cidLen);
            }
            String updateSql = "UPDATE user u SET u.shoppingCart = '" + cartSb.toString() + "' WHERE name = '" + name + "'";
            this.ju.cud(updateSql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int loginCheck(String name, String password) {
        try {
            String sql = "SELECT * FROM user";
            ResultSet rs = this.ju.query(sql);
            String userName = null;
            while(rs.next()) {
                if (rs.getString("name").equals(name) && rs.getString("password").equals(password)) {
                    return rs.getInt("id");
                }
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int register(String name, String password) {
        try {
            String sql = "INSERT INTO user (name,password,shoppingCart,goods) VALUES ('" + name + "','" + password + "','[]','[]')";
            this.ju.cud(sql);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public ArrayList<Commodity> getShoppingCart(String name) {
        try {
            String sql = "SELECT * FROM user WHERE name = '" + name + "'";
            ResultSet rs = this.ju.query(sql);
            rs.next();
            String cart = rs.getString("shoppingCart");
            StringBuilder cartSb = new StringBuilder(cart);
            cartSb.delete(0, 1);
            cartSb.deleteCharAt(cartSb.length() - 1);
            if ("".equals(cartSb.toString())) {
                return new ArrayList<Commodity>();
            }
            String[] carts = cartSb.toString().split(",");
            ArrayList<Commodity> cartList = new ArrayList<>();
            for (String c : carts) {
                String comSql = "SELECT * FROM commodity WHERE id = " + c;
                ResultSet comRs = this.ju.query(comSql);
                comRs.next();
                cartList.add(new Commodity(comRs.getInt("id"),
                        comRs.getString("name"),
                        comRs.getString("image"),
                        comRs.getDouble("price"),
                        comRs.getString("desc")));
            }
            return cartList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Commodity> getGoods(String name) {
        try {
            String sql = "SELECT * FROM user WHERE name = '" + name + "'";
            ResultSet rs = this.ju.query(sql);
            String goods = null;
            while(rs.next()) {
                goods = rs.getString("goods");
            }
            StringBuilder goodsSb = new StringBuilder(goods);
            goodsSb.delete(0, 1);
            goodsSb.deleteCharAt(goodsSb.length() - 1);
            if ("".equals(goodsSb.toString())) {
                return new ArrayList<Commodity>();
            }
            String[] goodsStr = goodsSb.toString().split(",");
            ArrayList<Commodity> goodsList = new ArrayList<>();
            for (String c : goodsStr) {
                String comSql = "SELECT * FROM commodity WHERE id = " + c;
                ResultSet comRs = this.ju.query(comSql);
                comRs.next();
                goodsList.add(new Commodity(comRs.getInt("id"),
                        comRs.getString("name"),
                        comRs.getString("image"),
                        comRs.getDouble("price"),
                        comRs.getString("desc")));
            }
            return goodsList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
