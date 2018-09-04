package com.dev.foodmovers.Views.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.foodmovers.Adapters.CartListAdapter;
import com.dev.foodmovers.Data.Models.FoodCartModel;
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


/**
 * Created by Eric on 1/24/2018.
 */

public class FragmentSaved extends Fragment {

    private LinearLayout linearPay;
    private View view;
    private RecyclerView recyclerView;
    private CartListAdapter listAdapter;

    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private LinearLayout empty_layout;
    private TextView emptyTxt, txt_network_state;
    private AVLoadingIndicatorView avi;
    private List<FoodCartModel> foodCartModels;
    private FoodViewModel viewModel;
    private Boolean isConnected;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppBarLayout appBarLayout = getActivity().findViewById(R.id.app_bar);
        appBarLayout.setExpanded(false);


        try {
            linearPay = getActivity().findViewById(R.id.linear_pay);


        } catch (Exception nm) {
            nm.printStackTrace();
        }
        if (linearPay != null) {
            linearPay.setOnClickListener(viewv -> {

            });
        }

        try {
            ((MainActivity) getActivity()).setSearchVisibility(View.GONE, false);

        } catch (Exception nm) {
            nm.printStackTrace();
        }

        viewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        this.view = view;

        initViews();
        getData();
    }

    private void getData() {
        viewModel.getCarts(false, getSearchObject()).observe(this, foodModels -> {
            if (foodModels != null) {

                for (int a = 0; a < foodModels.size(); a++) {

                }
                Log.d("foodgg", "" + foodModels.size());
                FragmentSaved.this.foodCartModels = foodModels;
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

    @Override
    public void onResume() {
        super.onResume();

    }

    void initViews() {

        recyclerView = view.findViewById(R.id.recyclerView);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        if (foodCartModels == null) {
            foodCartModels = new LinkedList<>();
        }
        listAdapter = new CartListAdapter(getActivity(), foodCartModels, new OnclickRecyclerListener() {
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

                calc(view, foodCartModels.get(adapterPosition));


            }
        });


        recyclerView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();


    }

    public void calc(View imgAction, FoodCartModel cartModel) {
        int gty = cartModel.getQuantity();
        double price = (Double.valueOf(cartModel.getPrice()));


        if (imgAction.getId() == R.id.img_add) {
            int vq = gty + 1;
            cartModel.setQuantity(vq);
            viewModel.update(cartModel);
        } else if (imgAction.getId() == R.id.img_remove) {
            int vq = gty;


            if (vq != 1) {
                cartModel.setQuantity(vq - 1);
                viewModel.update(cartModel);


            }
        } else if (imgAction.getId() == R.id.img_delete) {
            viewModel.delete(cartModel);
        }
    }


}
