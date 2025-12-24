package com.example.spring_security_inRAM_part2.model;

public class Products {

    private Integer proId;
    private String name;
    private Integer qty;
    private Double price;
    private String desc;

    public Products(Integer proId, String name, Integer qty, Double price, String desc) {
        this.proId = proId;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.desc = desc;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
