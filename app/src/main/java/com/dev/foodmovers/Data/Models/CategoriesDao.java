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
public interface CategoriesDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMultipleCategories(CategoriesModel... f);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMultipleCategories(List<CategoriesModel> categories);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSingleCategories(CategoriesModel category);


    @Query("SELECT * FROM categories")
    LiveData<List<CategoriesModel>> fetchAllData();


    @Query("SELECT * FROM categories WHERE name LIKE :shortname")
    LiveData<List<CategoriesModel>> searchbyName(String shortname);

    @Query("SELECT * FROM categories ORDER BY id DESC LIMIT 1")
    LiveData<CategoriesModel> getLastCategories();


    @Query("SELECT * FROM categories WHERE id =:keyid")
    LiveData<CategoriesModel> getCategoriesByKeyID(int keyid);


    @Query("SELECT COUNT(code) FROM categories ")
    int getNumberOfRows();

    @Update
    void updateRecord(CategoriesModel category);

    @Delete
    void deleteRecord(CategoriesModel category);


}
