package com.dev.foodmovers.Data.Models;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "foodmodel", indices = {@Index(value = {"code"}, unique = true)})

public class FoodModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String image1;
    private String image2;
    private String image3;
    private String price;
    private String discount;
    private String departmentcode;
    private String departmentname;
    private String categorycode;
    private String categoryname;
    private String restaurantcode;
    private String restaurantname;
    private String tags;
    private String timestamp;
    private int status;
    private String statusname;
    private String taxcode;
    private String tax;

    public FoodModel() {
    }

    public FoodModel(int id, String code, String name, String description, String image, String image1, String image2, String image3, String price, String discount, String departmentcode, String departmentname, String categorycode, String categoryname, String restaurantcode, String restaurantname, String tags, String timestamp, int status, String statusname, String taxcode, String tax) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.image = image;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.price = price;
        this.discount = discount;
        this.departmentcode = departmentcode;
        this.departmentname = departmentname;
        this.categorycode = categorycode;
        this.categoryname = categoryname;
        this.restaurantcode = restaurantcode;
        this.restaurantname = restaurantname;
        this.tags = tags;
        this.timestamp = timestamp;
        this.status = status;
        this.statusname = statusname;
        this.taxcode = taxcode;
        this.tax = tax;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
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

    public String getDepartmentcode() {
        return departmentcode;
    }

    public void setDepartmentcode(String departmentcode) {
        this.departmentcode = departmentcode;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getCategorycode() {
        return categorycode;
    }

    public void setCategorycode(String categorycode) {
        this.categorycode = categorycode;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getRestaurantcode() {
        return restaurantcode;
    }

    public void setRestaurantcode(String restaurantcode) {
        this.restaurantcode = restaurantcode;
    }

    public String getRestaurantname() {
        return restaurantname;
    }

    public void setRestaurantname(String restaurantname) {
        this.restaurantname = restaurantname;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

    public String getTaxcode() {
        return taxcode;
    }

    public void setTaxcode(String taxcode) {
        this.taxcode = taxcode;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }
}
