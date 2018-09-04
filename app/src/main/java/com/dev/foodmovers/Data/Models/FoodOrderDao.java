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
public interface FoodOrderDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMultipleFoodOrder(FoodOrderModel... foodOrderModels);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMultipleFoodOrder(List<FoodOrderModel> foodOrderModels);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSingleFoodOrder(FoodOrderModel foodOrderModel);


    @Query("SELECT * FROM FOODORDERMODEL")
    LiveData<List<FoodOrderModel>> fetchAllData();


    @Query("SELECT * FROM FOODORDERMODEL WHERE code LIKE :code")
    LiveData<List<FoodOrderModel>> searchbyName(String code);

    @Query("SELECT * FROM FOODORDERMODEL ORDER BY id DESC LIMIT 1")
    LiveData<FoodOrderModel> getLastFoodOrder();


    @Query("SELECT * FROM FOODORDERMODEL WHERE id =:keyid")
    LiveData<FoodOrderModel> getFoodOrderByKeyID(int keyid);

    @Query("SELECT * FROM FOODORDERMODEL WHERE status =:status")
    LiveData<List<FoodOrderModel>> getAllByOrderStatus(int status);


    @Query("SELECT * FROM FOODORDERMODEL WHERE timestamp LIKE :datetime")
    LiveData<List<FoodOrderModel>> searchByFoodOrderByDateTime(String datetime);

    @Query("SELECT COUNT(code) FROM FOODORDERMODEL ")
    int getNumberOfRows();

    @Update
    void updateRecord(FoodOrderModel foodModel);

    @Delete
    void deleteRecord(FoodOrderModel foodModel);


}
