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
public interface RestaurantsDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMultipleRestaurants(RestaurantsModel... f);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMultipleRestaurants(List<RestaurantsModel> restaurants);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSingleRestaurants(RestaurantsModel restaurant);


    @Query("SELECT * FROM restaurant")
    LiveData<List<RestaurantsModel>> fetchAllData();


    @Query("SELECT * FROM restaurant WHERE name LIKE :shortname")
    LiveData<List<RestaurantsModel>> searchbyName(String shortname);

    @Query("SELECT * FROM restaurant ORDER BY id DESC LIMIT 1")
    LiveData<RestaurantsModel> getLastRestaurants();


    @Query("SELECT * FROM restaurant WHERE id =:keyid")
    LiveData<RestaurantsModel> getRestaurantsByKeyID(int keyid);


    @Query("SELECT COUNT(code) FROM restaurant ")
    int getNumberOfRows(int av);

    @Update
    void updateRecord(RestaurantsModel restaurant);

    @Delete
    void deleteRecord(RestaurantsModel restaurant);


}
