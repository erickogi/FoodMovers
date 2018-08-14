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
    private String fullname;
    private String shortname;
    private String price;
    private String discount;
    private int hasDiscount = 1;
    private String description;

    //Dessert etc;

    private String foodType;
    private int foodTypeCode;

    //Lunch , breakfast,dinner etc


    private String foodCourse;
    private String foodCourseCode;

    // 1 is available 0 is not available

    private int availability = 1;

    //How many people does it serve


    private int serves = 1;

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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
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

    public int getHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(int hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodCourse() {
        return foodCourse;
    }

    public void setFoodCourse(String foodCourse) {
        this.foodCourse = foodCourse;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public int getServes() {
        return serves;
    }

    public void setServes(int serves) {
        this.serves = serves;
    }
}
