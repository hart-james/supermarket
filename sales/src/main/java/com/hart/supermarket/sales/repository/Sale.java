package com.hart.supermarket.sales.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Document(collection="Sale")
public class Sale implements Serializable {

    @Id
    private String uuid;

    private int time;
    private List<String> itemsSku;
    private double total;
    private String type;
    private String employee;

    public Sale(String uuid, int time, List<String> itemsSku, double total,
                String type, String employee) {
        this.uuid = uuid;
        this.time = time;
        this.itemsSku = itemsSku;
        this.total = total;
        this.type = type;
        this.employee = employee;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid() {
        this.uuid = UUID.randomUUID().toString();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<String> getItemsSku() {
        return itemsSku;
    }

    public void setItemsSku(List<String> itemsSku) {
        this.itemsSku = itemsSku;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "uuid='" + uuid + '\'' +
                ", time=" + time +
                ", itemsSku=" + itemsSku +
                ", total=" + total +
                ", type='" + type + '\'' +
                ", employee='" + employee + '\'' +
                '}';
    }
}
