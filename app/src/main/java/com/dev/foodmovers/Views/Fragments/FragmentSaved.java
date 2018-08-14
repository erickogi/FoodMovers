package com.dev.foodmovers.Views.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dev.foodmovers.MainActivity;
import com.dev.foodmovers.R;


/**
 * Created by Eric on 1/24/2018.
 */

public class FragmentSaved extends Fragment {

    private LinearLayout linearPay;
    private View view;

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
            ((MainActivity) getActivity()).setSearchVisibility(View.GONE);

        } catch (Exception nm) {
            nm.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

}
