package com.model;

public class Commodity {
    private int id = 0;
    private String name = null;
    private String image = null;
    private Double price = null;
    private String desc = null;

    public Commodity(int id,String name, String image, Double price, String desc) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.desc = desc;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Double getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", desc='" + desc + '\'' +
                '}';
    }
}
