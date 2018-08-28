package com.dev.foodmovers.Views.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dev.foodmovers.Adapters.FoodListAdapter;
import com.dev.foodmovers.Constants;
import com.dev.foodmovers.Data.Models.FoodModel;
import com.dev.foodmovers.Data.Models.FoodSearchOject;
import com.dev.foodmovers.MainActivity;
import com.dev.foodmovers.R;
import com.dev.foodmovers.ViewModels.FoodViewModel;
import com.dev.lishabora.Utils.OnclickRecyclerListener;
import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


/**
 * Created by Eric on 3/6/2018.
 */

public class FragmentProducts extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private FoodListAdapter listAdapter;
    private View view;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private LinearLayout empty_layout;
    private TextView emptyTxt, txt_network_state;
    private AVLoadingIndicatorView avi;
    private List<FoodModel> foodModels;
    private FoodViewModel viewModel;
    private Boolean isConnected;

    private RequestOptions options;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AppBarLayout appBarLayout = getActivity().findViewById(R.id.app_bar);
        appBarLayout.setExpanded(true);

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            ((MainActivity) Objects.requireNonNull(getActivity())).setSearchVisibility(View.VISIBLE);
        } catch (Exception nm) {
            nm.printStackTrace();
        }
        viewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        this.view = view;

        options = (new RequestOptions())
                .placeholder(R.drawable.imagepicker_image_placeholder)
                .error(R.drawable.imagepicker_image_placeholder)
                .centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        initViews();


        getData();
    }

    private void getData() {
        viewModel.getFoods(Constants.isConnected(), getSearchObject(), "", "", "").observe(this, foodModels -> {
            if (foodModels != null) {
                FragmentProducts.this.foodModels = foodModels;
                listAdapter.refresh(foodModels);
            }
        });
    }

    private JSONObject getSearchObject() {

        FoodSearchOject f = new FoodSearchOject();


        Gson gson = new Gson();
        f.setCategorycode("");
        f.setDepartmentcode("");
        f.setDepartmentcode("");
        f.setName("");
        f.setTags("");
        try {
            return new JSONObject(gson.toJson(f));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;


    }

    void initViews() {

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView = view.findViewById(R.id.recyclerView);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        if (foodModels == null) {
            foodModels = new LinkedList<>();
        }
        listAdapter = new FoodListAdapter(getActivity(), foodModels, new OnclickRecyclerListener() {
            @Override
            public void onSwipe(int adapterPosition, int direction) {


            }

            @Override
            public void onClickListener(int position) {


            }

            @Override
            public void onLongClickListener(int position) {


            }

            @Override
            public void onCheckedClickListener(int position) {

            }

            @Override
            public void onMoreClickListener(int position) {

            }

            @Override
            public void onClickListener(int adapterPosition, @NotNull View view) {


            }
        });
        recyclerView.setAdapter(listAdapter);

        listAdapter.notifyDataSetChanged();


    }

    void dialog(FoodModel model) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
        View mView = layoutInflaterAndroid.inflate(R.layout.dialog_food_details, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        alertDialogBuilderUserInput.setView(mView);

        TextView txtName, txtQty, txtPrice;
        ImageView imgFood, imgAdd, imgRemove, imgDelete;
        MaterialButton btnAdd;

        btnAdd = mView.findViewById(R.id.btn_add_to_cart);

        imgFood = mView.findViewById(R.id.img_food);
        imgFood.setOnClickListener(this);

        txtName = mView.findViewById(R.id.txt_name);
        txtQty = mView.findViewById(R.id.txt_qty);
        txtPrice = mView.findViewById(R.id.txt_price);

        imgAdd = mView.findViewById(R.id.img_add);
        imgRemove = mView.findViewById(R.id.img_remove);
        imgDelete = mView.findViewById(R.id.img_delete);


        imgAdd.setOnClickListener(this);
        imgRemove.setOnClickListener(this);
        imgDelete.setOnClickListener(this);
        btnAdd.setOnClickListener(this);


        txtName.setText(model.getName());
        txtQty.setText("1");
        txtPrice.setText(model.getPrice());

        Glide.with(getContext())
                .load(model.getImage())
                .apply(options)
                .into(imgFood);


    }


    @Override
    public void onClick(View view) {

    }
}
