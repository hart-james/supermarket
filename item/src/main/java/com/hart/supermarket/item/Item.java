package com.hart.supermarket.item;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document
public class Item {

    private String name;
    private String brand;
    private String section;
    private double price;
    private String sku;
    private Date expiration;
    private boolean sale;

    public Item(String name, String brand, String section, double price, String sku, Date expiration, boolean sale) {
        this.name = name;
        this.brand = brand;
        this.section = section;
        this.price = price;
        this.sku = sku;
        this.expiration = expiration;
        this.sale = sale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "ItemsService{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", section='" + section + '\'' +
                ", price=" + price +
                ", sku=" + sku +
                ", expiration=" + expiration +
                ", sale=" + sale +
                '}';
    }
}
