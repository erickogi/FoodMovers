package com.dev.foodmovers.Data.Models;

public class FoodSearchOject {
    private String departmentcode;
    private String restaurantcode;
    private String tags;
    private String categorycode;
    private String name;

    public FoodSearchOject() {
    }

    public FoodSearchOject(String departmentcode, String restaurantcode, String tags, String categorycode, String name) {
        this.departmentcode = departmentcode;
        this.restaurantcode = restaurantcode;
        this.tags = tags;
        this.categorycode = categorycode;
        this.name = name;
    }

    public String getDepartmentcode() {
        return departmentcode;
    }

    public void setDepartmentcode(String departmentcode) {
        this.departmentcode = departmentcode;
    }

    public String getRestaurantcode() {
        return restaurantcode;
    }

    public void setRestaurantcode(String restaurantcode) {
        this.restaurantcode = restaurantcode;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategorycode() {
        return categorycode;
    }

    public void setCategorycode(String categorycode) {
        this.categorycode = categorycode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
