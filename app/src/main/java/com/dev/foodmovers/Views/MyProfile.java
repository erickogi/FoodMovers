package com.dev.foodmovers.Views;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.foodmovers.Data.PrefManager;
import com.dev.foodmovers.Data.UserModel;
import com.dev.foodmovers.Kogi.Utils.GeneralUtills;
import com.dev.foodmovers.R;

import java.util.HashMap;

//import com.android.volley.VolleyError;

public class MyProfile extends AppCompatActivity {
    private final int CAMERA_REQUEST = 1888;
    PrefManager prefManager;
    UserModel userModel = null;
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;
    private Bitmap bitmap;
    private String decodeBitmap = null;
    private Button btnUpdate;
    private EditText mFirstName, mLastName, mEmail, mMobile, mPassword;
    private TextView txtTakePhoto;
    private ImageView imageCamera;
    private String imagePath = "1";
    private RelativeLayout relativeLayoutCamera;

    private static boolean isValidPhoneNumber(String mobile) {
        String regEx = "^[0-9]{12}$";
        return mobile.matches(regEx);
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        prefManager = new PrefManager(MyProfile.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        relativeLayoutCamera = findViewById(R.id.camera);
        txtTakePhoto = findViewById(R.id.txt_take_photo);
        imageCamera = findViewById(R.id.take_image);
        mFirstName = findViewById(R.id.txt_firstname);
        mLastName = findViewById(R.id.txt_lastname);
        mEmail = findViewById(R.id.txt_emailAdress);
        mMobile = findViewById(R.id.txt_mobile);
        btnUpdate = findViewById(R.id.btnUpdate);
        // mPassword=findViewById(R.id.txt_password);


        try {

            mFirstName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_person_black_24dp, 0, 0, 0);
            mLastName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_person_black_24dp, 0, 0, 0);
            mEmail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_email_black_24dp, 0, 0, 0);
            mMobile.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_phone_black_24dp, 0, 0, 0);
            // mPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock_black_24dp,0,0,0);
            // mEmail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_lock_black_24dp,0,0,0);


        } catch (Exception hj) {

            hj.printStackTrace();
        }


        userModel = prefManager.getUserData();

        if (userModel != null) {
            try {
                mFirstName.setText(userModel.getFirstName());
                mLastName.setText(userModel.getLastName());
                mEmail.setText(userModel.getEmail());
                mMobile.setText(userModel.getPhoneNumber());


                decodeBitmap = userModel.getPhoto();

//                Glide.with(MyProfile.this)
//                        .load(userModel.getPhoto())
//                        //.apply(options)
//                        .into(imageCamera);


                // imageCamera.setImageBitmap(GeneralUtills.base64ToBitmap(userModel.getPhoto()));

            } catch (Exception nm) {

                nm.printStackTrace();
            }
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.hide();


    }

    private boolean validateFields() {
        if (!isValidEmail(mEmail.getText().toString())) {
            mEmail.setError("Invalid email");
            return false;
        } else {
            return true;
        }


    }

    private boolean isFilled(TextInputEditText textInputEditText) {
        if (textInputEditText.getText().toString().equals("")) {
            textInputEditText.setError("Required");
            return false;
        } else {
            return true;
        }

    }

    public void update(View view) {

        if (isDataChanged()) {
            if (GeneralUtills.isFilledTextInputEditText(mFirstName) && GeneralUtills.isFilledTextInputEditText(mLastName) &&
                    GeneralUtills.isFilledTextInputEditText(mEmail) && GeneralUtills.isFilledTextInputEditText(mMobile) && decodeBitmap != null) {

                if (validateFields()) {

                    progressDialog = new ProgressDialog(MyProfile.this);
                    progressDialog.setMessage("Updating....");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    String name = mFirstName.getText().toString();
                    String email = mEmail.getText().toString();
                    String mobile = mMobile.getText().toString();


                }
            } else {

            }
        } else {
            snack("Data has not changed", view);
        }

    }

    private boolean isDataChanged() {

        if (userModel != null) {

            if (!mFirstName.getText().toString().equals(userModel.getFirstName())) {
                return true;
            }
            if (!mLastName.getText().toString().equals(userModel.getLastName())) {
                return true;
            }
            if (!mEmail.getText().toString().equals(userModel.getEmail())) {
                return true;
            }
            return !mMobile.getText().toString().equals(userModel.getPhoneNumber());


        } else {
            return true;
        }
    }

    private void snack(String msg, View view) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    private void updates(int userId, String name, String email, String mobile, String decodeBitmap) {

        UserModel userModel = new UserModel();
        userModel.setUserId(prefManager.getUserData().getUserId());
        userModel.setPhoto(decodeBitmap);
        userModel.setPhoto(imagePath);
        userModel.setPhoneNumber(mobile);
        userModel.setEmail(email);
        userModel.setFirstName(mFirstName.getText().toString());
        userModel.setLastName(mLastName.getText().toString());
        userModel.setUserName(name);
        prefManager.setUserData(userModel);

        HashMap<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("user", "PHOTOZURI");
        params.put("mobile", mobile);
        params.put("image", decodeBitmap);
        params.put("id", String.valueOf(userId));


    }
}
