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
public interface DepartmentsDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMultipleDepartments(DepartmentsModel... f);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMultipleDepartments(List<DepartmentsModel> departments);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSingleDepartments(DepartmentsModel department);


    @Query("SELECT * FROM department")
    LiveData<List<DepartmentsModel>> fetchAllData();


    @Query("SELECT * FROM department WHERE name LIKE :shortname")
    LiveData<List<DepartmentsModel>> searchbyName(String shortname);

    @Query("SELECT * FROM department ORDER BY id DESC LIMIT 1")
    LiveData<DepartmentsModel> getLastDepartments();


    @Query("SELECT * FROM department WHERE id =:keyid")
    LiveData<DepartmentsModel> getDepartmentsByKeyID(int keyid);


    @Query("SELECT COUNT(code) FROM department ")
    int getNumberOfRows(int av);

    @Update
    void updateRecord(DepartmentsModel department);

    @Delete
    void deleteRecord(DepartmentsModel department);


}
