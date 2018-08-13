package com.dev.foodmovers.Views.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dev.foodmovers.Kogi.Utils.SampleHelper;
import com.dev.foodmovers.R;


public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        SampleHelper.with(this).init().loadAbout();
    }
}
