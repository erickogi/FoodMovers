package com.dev.foodmovers.Data;

import com.dev.foodmovers.Data.Models.CategoriesModel;
import com.dev.foodmovers.Data.Models.DepartmentsModel;
import com.dev.foodmovers.Data.Models.FoodModel;
import com.dev.foodmovers.Data.Models.RestaurantsModel;
import com.dev.foodmovers.Kogi.Utils.DateTimeUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DataGen {
    public static List<RestaurantsModel> generateRestaurant() {
        List<RestaurantsModel> restaurantsModels = new LinkedList<>();

        for (int a = 0; a < 20; a++) {
            restaurantsModels.add(new RestaurantsModel(1, "" + a + "010", "Restaurant" + a, "" + a, "" + a));


        }
        return restaurantsModels;
    }

    public static List<CategoriesModel> generateCategory() {
        List<CategoriesModel> categoriesModels = new LinkedList<>();

        for (int a = 0; a < 20; a++) {
            categoriesModels.add(new CategoriesModel(1, "" + a + "010", "Category" + a));


        }
        return categoriesModels;
    }

    public static List<DepartmentsModel> generateDepartment() {
        List<DepartmentsModel> departmentsModels = new LinkedList<>();

        for (int a = 0; a < 20; a++) {
            departmentsModels.add(new DepartmentsModel(1, "" + a + "010", "Department" + a));


        }
        return departmentsModels;
    }

    public static List<FoodModel> generateFoods() {
        Random rand = new Random();


        List<FoodModel> foodModels = new LinkedList<>();
        for (int a = 0; a < 100; a++) {
            int rn = rand.nextInt(19) + 1;
            int dn = rand.nextInt(19) + 1;
            int cn = rand.nextInt(19) + 1;

            FoodModel f = new FoodModel();
            f.setName("Food " + a);
            f.setCode("010" + a);
            f.setCategorycode(generateCategory().get(cn).getCode());
            f.setCategoryname(generateCategory().get(cn).getName());
            f.setDepartmentcode(generateDepartment().get(dn).getCode());
            f.setDepartmentname(generateDepartment().get(dn).getName());
            f.setRestaurantcode(generateRestaurant().get(rn).getCode());
            f.setDepartmentname(generateRestaurant().get(rn).getName());
            f.setDescription("Food description is here ");
            f.setImage("https://i1.wp.com/www.foodeverest.com/wp-content/uploads/2016/12/beef-burger.jpg?fit=4592%2C3448");
            f.setPrice("" + a + rn + "00");
            f.setDiscount("0.0");

            f.setTimestamp(DateTimeUtils.getNow());
            f.setStatus(1);
            f.setStatusname("Available");

            foodModels.add(f);
        }

        return foodModels;
    }
}
