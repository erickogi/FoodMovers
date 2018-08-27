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

public class DepartmentRepo {
    private CategoriesDao daao;

    private FMDatabase db;
    private Gson gson;


    public DepartmentRepo(Application application) {
        db = FMDatabase.getDatabase(application);
        daao = db.categoriesDao();
    }


    public LiveData<List<CategoriesModel>> fetchAllData(boolean isOnline, @Nullable String url, @Nullable JSONObject jsonObject) {

        if (isOnline) {
            Request.Companion.getResponse(url, jsonObject, "", new ResponseCallback() {
                        @Override
                        public void response(ResponseModel responseModel) {
                            JsonArray jsonArray = gson.toJsonTree(responseModel.getData()).getAsJsonArray();
                            Type listType = new TypeToken<LinkedList<CategoriesModel>>() {
                            }.getType();
                            insert(gson.fromJson(jsonArray, listType));


                        }

                        @Override
                        public void response(ResponseObject responseModel) {
                            JsonArray jsonArray = gson.toJsonTree(responseModel.getData()).getAsJsonArray();
                            Type listType = new TypeToken<LinkedList<CategoriesModel>>() {
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


    public void insert(List<CategoriesModel> foodModels) {
        daao = db.categoriesDao();

        new insertAsyncTask(daao).execute(foodModels);
    }

    public LiveData<CategoriesModel> getLastFood() {
        return db.categoriesDao().getLastCategories();
    }

    public LiveData<List<CategoriesModel>> searchByName(String name) {
        return db.categoriesDao().searchbyName(name);

    }

    public void delete(CategoriesModel model) {
        new deleteAsyncTask(db.categoriesDao()).execute(model);

    }

    public void update(CategoriesModel model) {
        new updateAsyncTask(db.categoriesDao()).execute(model);

    }

    private static class insertAsyncTask extends AsyncTask<List<CategoriesModel>, Void, Boolean> {

        private CategoriesDao mAsyncTaskDao;

        insertAsyncTask(CategoriesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final List<CategoriesModel>... params) {
            mAsyncTaskDao.insertMultipleCategories(params[0]);
            return true;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


        }
    }

    private static class insertAsyncSingleTask extends AsyncTask<CategoriesModel, Void, Boolean> {

        private CategoriesDao mAsyncTaskDao;

        insertAsyncSingleTask(CategoriesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final CategoriesModel... params) {
            mAsyncTaskDao.insertSingleCategories(params[0]);
            return true;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


        }
    }

    private static class updateAsyncTask extends AsyncTask<CategoriesModel, Void, Boolean> {

        private CategoriesDao mAsyncTaskDao;

        updateAsyncTask(CategoriesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final CategoriesModel... params) {
            mAsyncTaskDao.updateRecord(params[0]);
            return true;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


        }
    }

    private static class deleteAsyncTask extends AsyncTask<CategoriesModel, Void, Boolean> {

        private CategoriesDao mAsyncTaskDao;

        deleteAsyncTask(CategoriesDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final CategoriesModel... params) {
            mAsyncTaskDao.deleteRecord(params[0]);
            return true;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


        }
    }


}
