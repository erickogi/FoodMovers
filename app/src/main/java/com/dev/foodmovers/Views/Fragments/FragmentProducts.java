package com.dev.foodmovers.Views.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.foodmovers.MainActivity;
import com.dev.foodmovers.R;

import java.util.Objects;


/**
 * Created by Eric on 3/6/2018.
 */

public class FragmentProducts extends Fragment {

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
            ((MainActivity) Objects.requireNonNull(getActivity())).setImagePayVisibility(View.GONE);
        } catch (Exception nm) {
            nm.printStackTrace();
        }

    }


}
