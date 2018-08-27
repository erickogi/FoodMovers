package com.dev.foodmovers.Data.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "foodordermodel", indices = {@Index(value = {"datetime"}, unique = true)})

public class FoodOrderModel implements Serializable {

    @PrimaryKey(autoGenerate = true)

    private int id;


    private String code;
    private String price;
    private String discount;
    private int status;
    private String statusname;
    private String clientid;
    private String timestamp;


    private String foodcartobjects;
    private List<FoodCartModel> foodCartModels;

    public FoodOrderModel() {
    }

    public FoodOrderModel(int id, String code, String price, String discount, int status, String statusname, String clientid, String timestamp) {
        this.id = id;
        this.code = code;
        this.price = price;
        this.discount = discount;
        this.status = status;
        this.statusname = statusname;
        this.clientid = clientid;
        this.timestamp = timestamp;
    }

    public String getFoodcartobjects() {
        return foodcartobjects;
    }

    public void setFoodcartobjects(String foodcartobjects) {
        this.foodcartobjects = foodcartobjects;
    }

    public List<FoodCartModel> getFoodCartModels() {
        return foodCartModels;
    }

    public void setFoodCartModels(List<FoodCartModel> foodCartModels) {
        this.foodCartModels = foodCartModels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
