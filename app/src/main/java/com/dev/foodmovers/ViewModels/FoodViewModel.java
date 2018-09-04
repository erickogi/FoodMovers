package com.dev.foodmovers.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.dev.foodmovers.Data.Models.CategoriesModel;
import com.dev.foodmovers.Data.Models.CategoryRepo;
import com.dev.foodmovers.Data.Models.DepartmentRepo;
import com.dev.foodmovers.Data.Models.DepartmentsModel;
import com.dev.foodmovers.Data.Models.FoodCartModel;
import com.dev.foodmovers.Data.Models.FoodCartRepo;
import com.dev.foodmovers.Data.Models.FoodModel;
import com.dev.foodmovers.Data.Models.FoodOrderModel;
import com.dev.foodmovers.Data.Models.FoodOrderRepo;
import com.dev.foodmovers.Data.Models.FoodRepo;
import com.dev.foodmovers.Data.Models.RestaurantRepo;
import com.dev.foodmovers.Data.Models.RestaurantsModel;
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
    private DepartmentRepo departmentRepo;
    private CategoryRepo categoryRepo;
    private RestaurantRepo restaurantRepo;


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


    private LiveData<List<RestaurantsModel>> restaurants;
    private LiveData<List<DepartmentsModel>> departments;
    private LiveData<List<CategoriesModel>> categories;





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
        categoryRepo = new CategoryRepo(application);
        departmentRepo = new DepartmentRepo(application);
        restaurantRepo = new RestaurantRepo(application);

    }


    public LiveData<List<FoodModel>> getFoods(boolean isOnline, JSONObject jso, String department, String category, String restaurant) {

        if (foods == null) {
            foods = new MutableLiveData<>();
        }
        foods = foodRepo.fetchAllData(isOnline, com.dev.lishabora.Utils.Network.ApiConstants.Companion.getFood(), jso, department, category, restaurant);
        return foods;
    }


    public LiveData<Integer> getCartCount() {
        return cartRepo.getCountFoodCart();
    }
    public LiveData<List<FoodCartModel>> getCarts(boolean isOnline, JSONObject jso) {

        if (carts == null) {
            carts = new MutableLiveData<>();
        }
        carts = cartRepo.fetchAllData(isOnline, com.dev.lishabora.Utils.Network.ApiConstants.Companion.getCarts(), jso);
        return carts;
    }

    public void insertCart(FoodCartModel model) {

        cartRepo.insert(model);
    }

    public void insertFood(List<FoodModel> model) {

        foodRepo.insert(model);
    }

    public void delete(FoodCartModel model) {
        cartRepo.delete(model);
    }

    public void update(FoodCartModel model) {
        cartRepo.update(model);
    }





    public LiveData<List<FoodOrderModel>> getOrders(boolean isOnline, JSONObject jso) {

        if (orders == null) {
            orders = new MutableLiveData<>();
        }
        orders = orderRepo.fetchAllData(isOnline, com.dev.lishabora.Utils.Network.ApiConstants.Companion.getOrders(), jso);
        return orders;
    }

    public void insertOrder(FoodOrderModel model) {

        orderRepo.insert(model);
    }

    public void insertOrder(List<FoodOrderModel> model) {

        orderRepo.insert(model);
    }

    public void delete(FoodOrderModel model) {
        orderRepo.delete(model);
    }

    public void update(FoodOrderModel model) {
        orderRepo.update(model);
    }


    public LiveData<List<DepartmentsModel>> getDepartments(boolean isOnline, JSONObject jso) {

        if (departments == null) {
            departments = new MutableLiveData<>();
        }
        departments = departmentRepo.fetchAllData(isOnline, com.dev.lishabora.Utils.Network.ApiConstants.Companion.getOrders(), jso);
        return departments;
    }

    public void insert(DepartmentsModel model) {

        departmentRepo.insert(model);
    }

    public void insertDepartment(List<DepartmentsModel> model) {

        departmentRepo.insert(model);
    }

    public void delete(DepartmentsModel model) {
        departmentRepo.delete(model);
    }

    public void update(DepartmentsModel model) {
        departmentRepo.update(model);
    }


    public LiveData<List<CategoriesModel>> getCaetories(boolean isOnline, JSONObject jso) {

        if (categories == null) {
            categories = new MutableLiveData<>();
        }
        categories = categoryRepo.fetchAllData(isOnline, com.dev.lishabora.Utils.Network.ApiConstants.Companion.getOrders(), jso);
        return categories;
    }

    public void insert(CategoriesModel model) {

        categoryRepo.insert(model);
    }

    public void insertCategory(List<CategoriesModel> model) {

        categoryRepo.insert(model);
    }

    public void delete(CategoriesModel model) {
        categoryRepo.delete(model);
    }

    public void update(CategoriesModel model) {
        categoryRepo.update(model);
    }


    public LiveData<List<RestaurantsModel>> getRestaurants(boolean isOnline, JSONObject jso) {

        if (restaurants == null) {
            restaurants = new MutableLiveData<>();
        }
        restaurants = restaurantRepo.fetchAllData(isOnline, com.dev.lishabora.Utils.Network.ApiConstants.Companion.getOrders(), jso);
        return restaurants;
    }

    public void insert(RestaurantsModel model) {

        restaurantRepo.insert(model);
    }

    public void insertRestaurant(List<RestaurantsModel> model) {

        restaurantRepo.insert(model);
    }

    public void delete(RestaurantsModel model) {
        restaurantRepo.delete(model);
    }

    public void update(RestaurantsModel model) {
        restaurantRepo.update(model);
    }




}
