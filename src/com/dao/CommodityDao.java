package com.dao;

import com.model.Commodity;

import java.util.ArrayList;

public interface CommodityDao {
    public boolean updateCommodity(Commodity c);
    public boolean deleteCommodity(int cid);
    public boolean createCommodity(Commodity c);
    public ArrayList<Commodity> commodityList();
    public Commodity getCommodity(int cid);
    public String getName(int cid);
}
