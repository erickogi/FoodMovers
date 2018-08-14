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


    @Query("SELECT * FROM FOODMODEL WHERE shortname LIKE :shortname")
    LiveData<List<FoodModel>> searchbyName(String shortname);

    @Query("SELECT * FROM FOODMODEL ORDER BY id DESC LIMIT 1")
    LiveData<FoodModel> getLastFoodFarmer();


    @Query("SELECT * FROM FOODMODEL WHERE id =:keyid")
    LiveData<FoodModel> getFoodByKeyID(int keyid);

    @Query("SELECT * FROM FOODMODEL WHERE availability =:availability")
    LiveData<List<FoodModel>> getAllByAvailability(int availability);

    @Query("SELECT * FROM FOODMODEL WHERE foodCourseCode =:code")
    LiveData<List<FoodModel>> getAllByCourseCode(int code);


    @Query("SELECT * FROM FOODMODEL WHERE foodTypeCode =:code")
    LiveData<List<FoodModel>> getAllByTypeCode(int code);


    @Query("SELECT * FROM FOODMODEL WHERE foodType LIKE :type")
    LiveData<List<FoodModel>> searchByFoodType(String type);

    @Query("SELECT * FROM FOODMODEL WHERE foodCourse LIKE :course")
    LiveData<List<FoodModel>> searchByFoodCourse(String course);


    @Query("SELECT * FROM FOODMODEL WHERE shortname LIKE :sname OR fullname LIKE :fname  ")
    LiveData<List<FoodModel>> search(String sname, String fname, int dummy, int deleted, int archived);

    @Query("SELECT COUNT(code) FROM FOODMODEL WHERE availability = :av")
    int getNumberOfRows(int av);

    @Update
    void updateRecord(FoodModel foodModel);

    @Delete
    void deleteRecord(FoodModel foodModel);


}
