package com.dev.foodmovers.Data.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "restaurant", indices = {@Index(value = {"code"}, unique = true)})

public class RestaurantsModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String code;
    private String name;
    private String phone;
    private String physicaladdress;


    public RestaurantsModel(int id, String code, String name, String phone, String physicaladdress) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.physicaladdress = physicaladdress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhysicaladdress() {
        return physicaladdress;
    }

    public void setPhysicaladdress(String physicaladdress) {
        this.physicaladdress = physicaladdress;
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
}

