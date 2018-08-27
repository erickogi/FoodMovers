package com.dev.foodmovers.Data.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "foodcartmodel", indices = {@Index(value = {"code"}, unique = true)})

public class FoodCartModel implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private int id;


    private String code;
    @Ignore
    FoodModel foodModel;
    private String foodid;
    private String price;
    private String foodname;
    private String discount;
    private int quantity = 1;
    private int status;
    private String statusname;
    private String timestamp;
    private String foodmodel;
    private String orderid;

    public FoodCartModel(int id, String code, String foodid, String foodname, String price, String discount, int quantity, int status, String statusname, String timestamp, String foodmodel, String orderid) {
        this.id = id;
        this.code = code;
        this.foodid = foodid;
        this.foodname = foodname;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.status = status;
        this.statusname = statusname;
        this.timestamp = timestamp;
        this.foodmodel = foodmodel;
        this.orderid = orderid;
    }

    public FoodCartModel(int id, String code, String foodid, String foodname, String price, String discount, int quantity, int status, String statusname, String timestamp, String foodmodel, String orderid, FoodModel foodModel) {
        this.id = id;
        this.code = code;
        this.foodid = foodid;
        this.foodname = foodname;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.status = status;
        this.statusname = statusname;
        this.timestamp = timestamp;
        this.foodmodel = foodmodel;
        this.orderid = orderid;
        this.foodModel = foodModel;
    }

    public FoodCartModel() {
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

    public String getFoodid() {
        return foodid;
    }

    public void setFoodid(String foodid) {
        this.foodid = foodid;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFoodmodel() {
        return foodmodel;
    }

    public void setFoodmodel(String foodmodel) {
        this.foodmodel = foodmodel;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public FoodModel getFoodModel() {
        return foodModel;
    }

    public void setFoodModel(FoodModel foodModel) {
        this.foodModel = foodModel;
    }
}
