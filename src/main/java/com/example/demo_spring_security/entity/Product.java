package com.example.demo_spring_security.entity;

public class Product {
    private Integer id;
    private String name;
    private Integer qty;
    private Double price;

    public Product(Integer id, String name, Integer qty, Double price) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
