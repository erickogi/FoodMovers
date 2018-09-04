package com.dev.foodmovers.Data.Models;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "department", indices = {@Index(value = {"code"}, unique = true)})

public class DepartmentsModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String code;
    private String name;

    public DepartmentsModel(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
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
