package com.dev.foodmovers.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.dev.foodmovers.Data.Models.CategoriesDao;
import com.dev.foodmovers.Data.Models.CategoriesModel;
import com.dev.foodmovers.Data.Models.DepartmentsDao;
import com.dev.foodmovers.Data.Models.DepartmentsModel;
import com.dev.foodmovers.Data.Models.FoodCartDao;
import com.dev.foodmovers.Data.Models.FoodCartModel;
import com.dev.foodmovers.Data.Models.FoodDao;
import com.dev.foodmovers.Data.Models.FoodModel;
import com.dev.foodmovers.Data.Models.FoodOrderDao;
import com.dev.foodmovers.Data.Models.FoodOrderModel;
import com.dev.foodmovers.Data.Models.RestaurantsDao;
import com.dev.foodmovers.Data.Models.RestaurantsModel;

@Database(entities = {FoodModel.class, FoodCartModel.class, FoodOrderModel.class, CategoriesModel.class, DepartmentsModel.class, RestaurantsModel.class}, version = 2)

public abstract class FMDatabase extends RoomDatabase {

    private static FMDatabase INSTANCE;

    public static FMDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (FMDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FMDatabase.class, "fm_database").fallbackToDestructiveMigration().allowMainThreadQueries()
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    public abstract FoodDao foodDao();

    public abstract FoodCartDao foodCartDao();

    public abstract FoodOrderDao foodOrderDao();

    public abstract CategoriesDao categoriesDao();

    public abstract DepartmentsDao departmentsDao();

    public abstract RestaurantsDao restaurantsDao();

}
