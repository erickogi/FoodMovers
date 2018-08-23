package com.dev.foodmovers.Data.Models;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.dev.foodmovers.Data.FMDatabase;
import com.dev.foodmovers.Network.ResponseCallback;
import com.dev.foodmovers.Network.ResponseModel;
import com.dev.foodmovers.Network.ResponseObject;
import com.dev.lishabora.Utils.Network.Request;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class FoodRepo {
    private FoodDao daao;

    private FMDatabase db;
    private Gson gson;


    public FoodRepo(Application application) {
        db = FMDatabase.getDatabase(application);
        daao = db.foodDao();
    }


    public LiveData<List<FoodModel>> fetchAllData(boolean isOnline, @Nullable String url, @Nullable JSONObject jsonObject) {

        if (isOnline) {
            Request.Companion.getResponse(url, jsonObject, "", new ResponseCallback() {
                        @Override
                        public void response(ResponseModel responseModel) {
                            JsonArray jsonArray = gson.toJsonTree(responseModel.getData()).getAsJsonArray();
                            Type listType = new TypeToken<LinkedList<FoodModel>>() {
                            }.getType();
                            insert(gson.fromJson(jsonArray, listType));


                        }

                        @Override
                        public void response(ResponseObject responseModel) {
                            JsonArray jsonArray = gson.toJsonTree(responseModel.getData()).getAsJsonArray();
                            Type listType = new TypeToken<LinkedList<FoodModel>>() {
                            }.getType();
                            insert(gson.fromJson(jsonArray, listType));

                        }
                    }
            );
            return null;
        } else {
        return daao.fetchAllData();
        }
    }


    public void insert(List<FoodModel> foodModels) {
        daao = db.foodDao();

        new insertAsyncTask(daao).execute(foodModels);
    }

    public LiveData<FoodModel> getLastFood() {
        return db.foodDao().getLastFood();
    }

    public LiveData<List<FoodModel>> searchByName(String name) {
        return db.foodDao().searchbyName(name);

    }

    private static class insertAsyncTask extends AsyncTask<List<FoodModel>, Void, Boolean> {

        private FoodDao mAsyncTaskDao;

        insertAsyncTask(FoodDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final List<FoodModel>... params) {
            mAsyncTaskDao.insertMultipleFood(params[0]);
            return true;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


        }
    }

    private static class insertAsyncSingleTask extends AsyncTask<FoodModel, Void, Boolean> {

        private FoodDao mAsyncTaskDao;

        insertAsyncSingleTask(FoodDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final FoodModel... params) {
            mAsyncTaskDao.insertSingleFood(params[0]);
            return true;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


        }
    }


}
