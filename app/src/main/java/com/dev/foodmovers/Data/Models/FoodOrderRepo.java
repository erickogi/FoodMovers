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

public class FoodOrderRepo {
    private FoodOrderDao daao;

    private FMDatabase db;
    private Gson gson;


    public FoodOrderRepo(Application application) {
        db = FMDatabase.getDatabase(application);
        daao = db.foodOrderDao();
    }


    public LiveData<List<FoodOrderModel>> fetchAllData(boolean isOnline, @Nullable String url, @Nullable JSONObject jsonObject) {

        if (isOnline) {
            Request.Companion.getResponse(url, jsonObject, "", new ResponseCallback() {
                        @Override
                        public void response(ResponseModel responseModel) {
                            JsonArray jsonArray = gson.toJsonTree(responseModel.getData()).getAsJsonArray();
                            Type listType = new TypeToken<LinkedList<FoodOrderModel>>() {
                            }.getType();
                            insert(gson.fromJson(jsonArray, listType));

                            //fetchAllData();

                        }

                        @Override
                        public void response(ResponseObject responseModel) {
                            JsonArray jsonArray = gson.toJsonTree(responseModel.getData()).getAsJsonArray();
                            Type listType = new TypeToken<LinkedList<FoodOrderRepo>>() {
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

    private LiveData<List<FoodOrderModel>> fetchAllData() {
        return daao.fetchAllData();

    }


    public void insert(List<FoodOrderModel> foodCartModels) {
        daao = db.foodOrderDao();

        new insertAsyncTask(daao).execute(foodCartModels);
    }

    public LiveData<FoodOrderModel> getLastFoodOrder() {
        return db.foodOrderDao().getLastFoodOrder();
    }

    public LiveData<List<FoodOrderModel>> searchByCode(String code) {
        return db.foodOrderDao().searchbyName(code);

    }

    public void delete(FoodOrderModel model) {
        new deleteAsyncTask(db.foodOrderDao()).execute(model);

    }

    public void update(FoodOrderModel model) {
        new updateAsyncTask(db.foodOrderDao()).execute(model);

    }

    private static class insertAsyncTask extends AsyncTask<List<FoodOrderModel>, Void, Boolean> {

        private FoodOrderDao mAsyncTaskDao;

        insertAsyncTask(FoodOrderDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final List<FoodOrderModel>... params) {
            mAsyncTaskDao.insertMultipleFoodOrder(params[0]);
            return true;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


        }
    }

    private static class insertAsyncSingleTask extends AsyncTask<FoodOrderModel, Void, Boolean> {

        private FoodOrderDao mAsyncTaskDao;

        insertAsyncSingleTask(FoodOrderDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final FoodOrderModel... params) {
            mAsyncTaskDao.insertSingleFoodOrder(params[0]);
            return true;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


        }
    }

    private static class updateAsyncTask extends AsyncTask<FoodOrderModel, Void, Boolean> {

        private FoodOrderDao mAsyncTaskDao;

        updateAsyncTask(FoodOrderDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final FoodOrderModel... params) {
            mAsyncTaskDao.updateRecord(params[0]);
            return true;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


        }
    }

    private static class deleteAsyncTask extends AsyncTask<FoodOrderModel, Void, Boolean> {

        private FoodOrderDao mAsyncTaskDao;

        deleteAsyncTask(FoodOrderDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final FoodOrderModel... params) {
            mAsyncTaskDao.deleteRecord(params[0]);
            return true;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


        }
    }


}
