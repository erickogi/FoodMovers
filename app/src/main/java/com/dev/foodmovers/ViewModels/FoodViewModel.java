package com.dev.foodmovers.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.dev.foodmovers.Data.Models.FoodCartModel;
import com.dev.foodmovers.Data.Models.FoodCartRepo;
import com.dev.foodmovers.Data.Models.FoodModel;
import com.dev.foodmovers.Data.Models.FoodOrderModel;
import com.dev.foodmovers.Data.Models.FoodOrderRepo;
import com.dev.foodmovers.Data.Models.FoodRepo;
import com.dev.foodmovers.Data.PrefManager;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class FoodViewModel extends AndroidViewModel {

    Gson gson = new Gson();

    private FoodRepo foodRepo;
    private FoodCartRepo cartRepo;
    private FoodOrderRepo orderRepo;


    private Application application;
    private PrefManager prefManager;

    private LiveData<List<FoodModel>> foods;
    private LiveData<FoodModel> food;
    private FoodModel foodModel;
    private List<FoodModel> foodModels;


    private LiveData<List<FoodCartModel>> carts;
    private LiveData<FoodCartModel> cart;
    private FoodCartModel foodCartModel;
    private List<FoodCartModel> foodCartModels;


    private LiveData<List<FoodOrderModel>> orders;
    private LiveData<FoodOrderModel> order;
    private FoodOrderModel foodOrderModel;
    private List<FoodOrderModel> foodOrderModels;


    private LiveData<Integer> count;


    public FoodViewModel(@NonNull Application application) {
        super(application);
        foods = new MutableLiveData<>();
        food = new MutableLiveData<>();

        foodModel = new FoodModel();
        foodModels = new LinkedList<>();

        foodRepo = new FoodRepo(application);
        cartRepo = new FoodCartRepo(application);
        orderRepo = new FoodOrderRepo(application);

    }


    public LiveData<List<FoodModel>> getFoods(boolean isOnline, JSONObject jso, String department, String category, String restaurant) {

        if (foods == null) {
            foods = new MutableLiveData<>();
        }
        foods = foodRepo.fetchAllData(isOnline, com.dev.lishabora.Utils.Network.ApiConstants.Companion.getFood(), jso, department, category, restaurant);
        return foods;
    }

    public void insertToCart(FoodCartModel model) {

        cartRepo.insert(model);
    }

    public void deleteCart(FoodCartModel model) {
        cartRepo.delete(model);
    }

    public void updateCart(FoodCartModel model) {
        cartRepo.update(model);
    }

    private LiveData<Integer> getCartCount() {
        return cartRepo.getCountFoodCart();
    }

    public LiveData<List<FoodCartModel>> getCarts(boolean isOnline, JSONObject jso) {

        if (carts == null) {
            carts = new MutableLiveData<>();
        }
        carts = cartRepo.fetchAllData(isOnline, com.dev.lishabora.Utils.Network.ApiConstants.Companion.getCarts(), jso);
        return carts;
    }

    public LiveData<List<FoodOrderModel>> getOrders(boolean isOnline, JSONObject jso) {

        if (orders == null) {
            orders = new MutableLiveData<>();
        }
        orders = orderRepo.fetchAllData(isOnline, com.dev.lishabora.Utils.Network.ApiConstants.Companion.getOrders(), jso);
        return orders;
    }

    public void insertToOrder(FoodOrderModel model) {

        orderRepo.insert(model);
    }

    public void insertToOrder(List<FoodOrderModel> model) {

        orderRepo.insert(model);
    }

    public void deleteOrder(FoodOrderModel model) {
        orderRepo.delete(model);
    }

    public void updateOrder(FoodOrderModel model) {
        orderRepo.update(model);
    }


}
