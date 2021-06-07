package com.hart.supermarket.item;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document(collection="Items")
public class Item {

    private String name;
    private String brand;
    private String section;
    private double price;
    @Id
    private String sku;
    private int expiration;
    private boolean sale;
    private int stocked;
    private String image;

    public Item(String name, String brand, String section, double price,
                String sku, int expiration, boolean sale, int stocked, String image) {
        this.name = name;
        this.brand = brand;
        this.section = section;
        this.price = price;
        this.sku = sku;
        this.expiration = expiration;
        this.sale = sale;
        this.stocked = stocked;
        this.image = image;
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

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public int getStocked() {
        return stocked;
    }

    public void setStocked(int stocked) {
        this.stocked = stocked;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void incrementItem(Item item) {
        item.stocked++;

    }

    public void decrementItem(Item item) {
        item.stocked--;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", section='" + section + '\'' +
                ", price=" + price +
                ", sku='" + sku + '\'' +
                ", expiration=" + expiration +
                ", sale=" + sale +
                ", stocked=" + stocked +
                ", image='" + image + '\'' +
                '}';
    }
}






