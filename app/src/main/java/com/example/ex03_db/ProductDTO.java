package com.example.ex03_db;

import java.io.Serializable;

public class ProductDTO implements Serializable {
    private int id;
    private String product_name;
    private int price;
    private int amount;

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", product_name='" + product_name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ProductDTO(String product_name, int price, int amount) {
        this.product_name = product_name;
        this.price = price;
        this.amount = amount;
    }

    public ProductDTO(int id, String product_name, int price, int amount) {
        this.id = id;
        this.product_name = product_name;
        this.price = price;
        this.amount = amount;
    }
}
