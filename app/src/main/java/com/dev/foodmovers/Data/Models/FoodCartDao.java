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
public interface FoodCartDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMultipleFoodCart(FoodCartModel... f);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMultipleFoodCart(List<FoodCartModel> foodCartModels);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSingleFoodCart(FoodCartModel foodCartModel);


    @Query("SELECT * FROM FOODCARTMODEL")
    LiveData<List<FoodCartModel>> fetchAllData();


    @Query("SELECT * FROM FOODCARTMODEL WHERE foodmodel LIKE :shortname")
    LiveData<List<FoodCartModel>> searchbyName(String shortname);

    @Query("SELECT * FROM FOODCARTMODEL ORDER BY id DESC LIMIT 1")
    LiveData<FoodCartModel> getLastFoodCart();


    @Query("SELECT * FROM FOODCARTMODEL WHERE id =:keyid")
    LiveData<FoodCartModel> getFoodCartByKeyID(int keyid);


    @Query("SELECT COUNT(code) FROM FOODCARTMODEL ")
    LiveData<Integer> getNumberOfRows(int av);

    @Update
    void updateRecord(FoodCartModel foodCartModel);

    @Delete
    void deleteRecord(FoodCartModel foodCartModel);


}
