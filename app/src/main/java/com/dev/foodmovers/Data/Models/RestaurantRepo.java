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

public class RestaurantRepo {
    private RestaurantsDao daao;

    private FMDatabase db;
    private Gson gson;


    public RestaurantRepo(Application application) {
        db = FMDatabase.getDatabase(application);
        daao = db.restaurantsDao();
    }


    public LiveData<List<RestaurantsModel>> fetchAllData(boolean isOnline, @Nullable String url, @Nullable JSONObject jsonObject) {

        if (isOnline) {
            Request.Companion.getResponse(url, jsonObject, "", new ResponseCallback() {
                        @Override
                        public void response(ResponseModel responseModel) {
                            JsonArray jsonArray = gson.toJsonTree(responseModel.getData()).getAsJsonArray();
                            Type listType = new TypeToken<LinkedList<RestaurantsModel>>() {
                            }.getType();
                            insert((List<RestaurantsModel>) gson.fromJson(jsonArray, listType));


                        }

                        @Override
                        public void response(ResponseObject responseModel) {
                            JsonArray jsonArray = gson.toJsonTree(responseModel.getData()).getAsJsonArray();
                            Type listType = new TypeToken<LinkedList<RestaurantsModel>>() {
                            }.getType();
                            insert((List<RestaurantsModel>) gson.fromJson(jsonArray, listType));

                        }
                    }
            );
            return null;
        } else {
            return daao.fetchAllData();
        }
    }


    public void insert(List<RestaurantsModel> restaurantsModels) {
        daao = db.restaurantsDao();

        new insertAsyncTask(daao).execute(restaurantsModels);
    }

    public void insert(RestaurantsModel restaurantsModels) {
        daao = db.restaurantsDao();

        new insertAsyncSingleTask(daao).execute(restaurantsModels);
    }


    public LiveData<RestaurantsModel> getLast() {
        return db.restaurantsDao().getLastRestaurants();
    }

    public LiveData<List<RestaurantsModel>> searchByName(String name) {
        return db.restaurantsDao().searchbyName(name);

    }

    public void delete(RestaurantsModel model) {
        new deleteAsyncTask(db.restaurantsDao()).execute(model);

    }

    public void update(RestaurantsModel model) {
        new updateAsyncTask(db.restaurantsDao()).execute(model);

    }

    private static class insertAsyncTask extends AsyncTask<List<RestaurantsModel>, Void, Boolean> {

        private RestaurantsDao mAsyncTaskDao;

        insertAsyncTask(RestaurantsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final List<RestaurantsModel>... params) {
            mAsyncTaskDao.insertMultipleRestaurants(params[0]);
            return true;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


        }
    }

    private static class insertAsyncSingleTask extends AsyncTask<RestaurantsModel, Void, Boolean> {

        private RestaurantsDao mAsyncTaskDao;

        insertAsyncSingleTask(RestaurantsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final RestaurantsModel... params) {
            mAsyncTaskDao.insertSingleRestaurants(params[0]);
            return true;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


        }
    }

    private static class updateAsyncTask extends AsyncTask<RestaurantsModel, Void, Boolean> {

        private RestaurantsDao mAsyncTaskDao;

        updateAsyncTask(RestaurantsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final RestaurantsModel... params) {
            mAsyncTaskDao.updateRecord(params[0]);
            return true;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


        }
    }

    private static class deleteAsyncTask extends AsyncTask<RestaurantsModel, Void, Boolean> {

        private RestaurantsDao mAsyncTaskDao;

        deleteAsyncTask(RestaurantsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final RestaurantsModel... params) {
            mAsyncTaskDao.deleteRecord(params[0]);
            return true;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);


        }
    }


}
