package com.dev.foodmovers.Data.Models;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface FoodDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMultipleFood(FoodModel... foodModels);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMultipleFood(List<FoodModel> foodModels);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSingleFood(FoodModel foodModel);


    @Query("SELECT * FROM FOODMODEL")
    LiveData<List<FoodModel>> fetchAllData();


    @Query("SELECT * FROM FOODMODEL WHERE name LIKE :name")
    LiveData<List<FoodModel>> searchbyName(String name);

    @Query("SELECT * FROM FOODMODEL ORDER BY id DESC LIMIT 1")
    LiveData<FoodModel> getLastFood();


    @Query("SELECT * FROM FOODMODEL WHERE id =:keyid")
    LiveData<FoodModel> getFoodByKeyID(int keyid);

    @Query("SELECT * FROM FOODMODEL WHERE status =:availability")
    LiveData<List<FoodModel>> getAllByAvailability(int availability);

    @Query("SELECT * FROM FOODMODEL WHERE departmentcode =:code")
    LiveData<List<FoodModel>> getAllByCourseCode(int code);


    @Query("SELECT * FROM FOODMODEL WHERE categorycode =:code")
    LiveData<List<FoodModel>> getAllByTypeCode(int code);


    @Query("SELECT * FROM FOODMODEL WHERE restaurantcode LIKE :code")
    LiveData<List<FoodModel>> searchByFoodType(int code);

    @Query("SELECT * FROM FOODMODEL WHERE tags LIKE :tags")
    LiveData<List<FoodModel>> searchByFoodCourse(String tags);


    @Query("SELECT COUNT(code) FROM FOODMODEL WHERE status = :av")
    int getNumberOfRows(int av);

    @Update
    void updateRecord(FoodModel foodModel);

    @Delete
    void deleteRecord(FoodModel foodModel);


}
