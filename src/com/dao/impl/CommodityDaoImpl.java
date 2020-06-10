package com.dao.impl;

import com.dao.CommodityDao;
import com.dao.JDBCUtil;
import com.model.Commodity;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CommodityDaoImpl implements CommodityDao {
    private JDBCUtil ju = new JDBCUtil();
    @Override
    public boolean updateCommodity(Commodity c) {
        try {
            String sql = "UPDATE commodity c SET c.name = '" + c.getName()+ "', c.image = '" + c.getImage() +
                    "', c.price = '" + c.getPrice() + "', c.desc = '" + c.getDesc() + "' WHERE c.id = " + c.getId();
            this.ju.cud(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCommodity(int cid) {
        try {
            String sql = "DELETE FROM commodity WHERE `id` = '" + cid + "'";
            this.ju.cud(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean createCommodity(Commodity c) {
        try {
            String sql = "INSERT INTO commodity (`name`, `image`, `price`, `desc`) VALUES ('" + c.getName() + "','" +
                    c.getImage() + "','" + c.getPrice() + "','" + c.getDesc() + "')";
            this.ju.cud(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Commodity> commodityList() {
        try {
            String sql = "SELECT * FROM commodity";
            ResultSet rs = this.ju.query(sql);
            ArrayList<Commodity> comList = new ArrayList<>();
            while(rs.next()) {
                comList.add(new Commodity(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getDouble("price"),
                        rs.getString("desc")));
            }
            return comList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Commodity getCommodity(int cid) {
        try {
            String sql = "SELECT * FROM commodity WHERE id = " + cid;
            ResultSet rs = this.ju.query(sql);
            rs.next();
            return new Commodity(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("image"),
                    rs.getDouble("price"),
                    rs.getString("desc"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public String getName(int cid) {
        try {
            String sql = "SELECT * FROM commodity WHERE id = " + cid;
            ResultSet rs = this.ju.query(sql);
            rs.next();
            return rs.getString("name");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
