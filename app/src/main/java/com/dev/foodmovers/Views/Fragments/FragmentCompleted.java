package com.dev.foodmovers.Views.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.foodmovers.MainActivity;
import com.dev.foodmovers.R;


//import com.android.volley.VolleyError;

/**
 * Created by Eric on 1/24/2018.
 */

public class FragmentCompleted extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CollapsingToolbarLayout collapsingToolbarLayout = getActivity().findViewById(R.id.collapsingAB_layout);
        AppBarLayout appBarLayout = getActivity().findViewById(R.id.app_bar);
        appBarLayout.setExpanded(false);
        try {
            ((MainActivity) getActivity()).setImagePayVisibility(View.GONE);
        } catch (Exception nm) {
            nm.printStackTrace();
        }
    }


}
