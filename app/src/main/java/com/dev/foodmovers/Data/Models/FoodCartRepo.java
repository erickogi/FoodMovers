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

public class FoodCartRepo {
    private FoodCartDao daao;

    private FMDatabase db;
    private Gson gson;


    public FoodCartRepo(Application application) {
        db = FMDatabase.getDatabase(application);
        daao = db.foodCartDao();
    }


    public LiveData<List<FoodCartModel>> fetchAllData(boolean isOnline, @Nullable String url, @Nullable JSONObject jsonObject) {

        if (isOnline) {
            Request.Companion.getResponse(url, jsonObject, "", new ResponseCallback() {
                        @Override
                        public void response(ResponseModel responseModel) {
                            JsonArray jsonArray = gson.toJsonTree(responseModel.getData()).getAsJsonArray();
                            Type listType = new TypeToken<LinkedList<FoodCartModel>>() {
                            }.getType();
                            insert(gson.fromJson(jsonArray, listType));

                            //fetchAllData();

                        }

                        @Override
                        public void response(ResponseObject responseModel) {
                            JsonArray jsonArray = gson.toJsonTree(responseModel.getData()).getAsJsonArray();
                            Type listType = new TypeToken<LinkedList<FoodCartRepo>>() {
                            }.getType();
                            insert(gson.fromJson(jsonArray, listType));

                            //fetchAllData();

                        }
                    }
            );
            return null;
        } else {
            return daao.fetchAllData();
        }
    }

    private LiveData<List<FoodCartModel>> fetchAllData() {
        return daao.fetchAllData();

    }


    public void insert(List<FoodCartModel> foodCartModels) {
        daao = db.foodCartDao();

        new insertAsyncTask(daao).execute(foodCartModels);
    }

    public LiveData<FoodCartModel> getLastFoodCart() {
        return db.foodCartDao().getLastFoodCart();
    }

    public LiveData<List<FoodCartModel>> searchByName(String name) {
        return db.foodCartDao().searchbyName(name);

    }

    public void delete(FoodCartModel cartModel) {
        new deleteAsyncTask(db.foodCartDao()).execute(cartModel);

    }

    public void update(FoodCartModel cartModel) {
        new updateAsyncTask(db.foodCartDao()).execute(cartModel);

    }

    private static class insertAsyncTask extends AsyncTask<List<FoodCartModel>, Void, Boolean> {

        private FoodCartDao mAsyncTaskDao;

        insertAsyncTask(FoodCartDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final List<FoodCartModel>... params) {
            mAsyncTaskDao.insertMultipleFoodCart(params[0]);
            return true;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


        }
    }

    private static class insertAsyncSingleTask extends AsyncTask<FoodCartModel, Void, Boolean> {

        private FoodCartDao mAsyncTaskDao;

        insertAsyncSingleTask(FoodCartDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final FoodCartModel... params) {
            mAsyncTaskDao.insertSingleFoodCart(params[0]);
            return true;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


        }
    }

    private static class updateAsyncTask extends AsyncTask<FoodCartModel, Void, Boolean> {

        private FoodCartDao mAsyncTaskDao;

        updateAsyncTask(FoodCartDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final FoodCartModel... params) {
            mAsyncTaskDao.updateRecord(params[0]);
            return true;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


        }
    }

    private static class deleteAsyncTask extends AsyncTask<FoodCartModel, Void, Boolean> {

        private FoodCartDao mAsyncTaskDao;

        deleteAsyncTask(FoodCartDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final FoodCartModel... params) {
            mAsyncTaskDao.deleteRecord(params[0]);
            return true;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


        }
    }


}
