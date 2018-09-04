package com.dev.foodmovers.Views.Login;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.androidnetworking.error.ANError;
import com.dev.foodmovers.Data.PrefManager;
import com.dev.foodmovers.Data.UserModel;
import com.dev.foodmovers.Data.UserModelParse;
import com.dev.foodmovers.Kogi.NetworkUtils;
import com.dev.foodmovers.MainActivity;
import com.dev.foodmovers.R;
import com.dev.lishabora.Utils.Network.ApiConstants;
import com.dev.lishabora.Utils.Network.Request;
import com.dev.lishabora.Utils.Network.RequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.dev.foodmovers.Kogi.Utils.GeneralUtills.isFilledTextInputEditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String EMAIL = "email";
    public static Fragment fragment = null;
    RelativeLayout scrollView;
    private Button btnRegister;
    private PrefManager prefrenceManager;
    private TextInputEditText mFirstName, mLastName, mEmail, mMobile, mPassword;
    private TextInputEditText mEditTextPassword, mEditTextUserId;
    private ProgressDialog progressDialog;
    private MaterialDialog dialog;
    private Button btnLogin;
    private String goooglePhoto = null;
    private TextView txtNotUser;
    private boolean goToPay = false;
    private boolean isMulti = true;
    private View view;

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private static boolean isValidPhoneNumber(String mobile) {
        String regEx = "^[0-9]{10}$";
        return mobile.matches(regEx);
    }

    private boolean isFilled(TextInputEditText textInputEditText) {
        if (textInputEditText.getText().toString().equals("")) {
            textInputEditText.requestFocus();
            textInputEditText.setError("Required");
            return false;
        } else {
            return true;
        }

    }

    private void snack(String msg) {
        Snackbar.make(scrollView, msg, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_fragment);


        scrollView = findViewById(R.id.scrollView_parent);

        Intent intent = getIntent();
        String Action = intent.getStringExtra("Action");


        txtNotUser = findViewById(R.id.createAccount);

        prefrenceManager = new PrefManager(LoginActivity.this);

        String id = prefrenceManager.getFbId();
        Log.d("idd", id);
        mEditTextPassword = findViewById(R.id.edt_passwordl);
        mEditTextUserId = findViewById(R.id.edt_user_idl);

        try {

            mEditTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password, 0, 0, 0);
            mEditTextUserId.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email, 0, 0, 0);


        } catch (Exception hj) {

            hj.printStackTrace();
        }
        btnLogin = findViewById(R.id.btn_login);
        //  key = getArguments().getInt("key");

        btnLogin.setOnClickListener(v -> {

            if (isFilledTextInputEditText(mEditTextPassword) && isFilled(mEditTextUserId)) {
                if (new NetworkUtils(LoginActivity.this).isNetworkAvailable()) {
                    //login1(1);
                    login(mEditTextUserId.getText().toString(), mEditTextPassword.getText().toString());
                } else {
                    snack("Couldn't connect Please check your internet connection");
                    //alertDialogDelete("Couldn't connect Please check your internet connection");
                }
            }

        });


        mFirstName = findViewById(R.id.txt_firstnames);
        mLastName = findViewById(R.id.txt_lastnames);
        mEmail = findViewById(R.id.txt_emailAdresss);
        mMobile = findViewById(R.id.txt_mobiles);
        mPassword = findViewById(R.id.txt_passwords);

        try {
            mEmail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email, 0, 0, 0);
            mPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password, 0, 0, 0);
            mFirstName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.user, 0, 0, 0);
            mLastName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.user, 0, 0, 0);
            mMobile.setCompoundDrawablesWithIntrinsicBounds(R.drawable.phone, 0, 0, 0);


        } catch (Exception hj) {

            hj.printStackTrace();
        }

        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(view -> {
            if (isFilledTextInputEditText(mFirstName)
                    && isFilledTextInputEditText(mLastName)
                    && isFilledTextInputEditText(mEmail)
                    && isFilledTextInputEditText(mMobile)
                    && isFilledTextInputEditText(mPassword)) {

                if (validateFields()) {

                    progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setMessage("Signing you in....");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setCancelable(true);
                    progressDialog.show();
                    String name = mFirstName.getText().toString();
                    String lastname = mLastName.getText().toString();
                    String email = mEmail.getText().toString();
                    String mobile = mMobile.getText().toString();


                    String token = "";


                }
            } else {

            }
        });


    }

    private boolean validateFields() {
        if (!isValidEmail(mEmail.getText().toString())) {
            mEmail.requestFocus();
            mEmail.setError("Invalid email");
            return false;
        } else if (!isValidPhoneNumber(mMobile.getText().toString())) {
            mMobile.requestFocus();
            mMobile.setError("Invalid Number");
            return false;
        } else {
            return true;
        }


    }


    @Override
    protected void onStart() {
        super.onStart();

    }


    void setUpView() {
        if (fragment != null) {
            FragmentManager fragmentManager = this.getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment)
                    .addToBackStack(null).commit();
        }

    }

    void popOutFragments() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
    }

    public void newAccountBtnPressed(View view) {

        changeView(1);


    }

    public void loginBtnPressed(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Logging you in....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();


    }

    public void newloginBtnPressed(View view) {
        //  popOutFragments();
        //fragment = new LoginFragment();
//        setUpView();

        changeView(2);


//
    }

    private void changeView(int i) {

        RelativeLayout relativeLayoutr = findViewById(R.id.register_view);
        RelativeLayout relativeLayoutl = findViewById(R.id.login_view);
        LinearLayout relativeLayoutf = findViewById(R.id.forgot_password_l);


        if (i == 1) {

            relativeLayoutr.setVisibility(View.VISIBLE);
            relativeLayoutl.setVisibility(View.GONE);
            relativeLayoutf.setVisibility(View.GONE);
        } else if (i == 2) {
            relativeLayoutr.setVisibility(View.GONE);
            relativeLayoutf.setVisibility(View.GONE);
            relativeLayoutl.setVisibility(View.VISIBLE);
        } else if (i == 3) {
            relativeLayoutr.setVisibility(View.GONE);
            relativeLayoutf.setVisibility(View.VISIBLE);
            relativeLayoutl.setVisibility(View.GONE);
        }

    }

    public void addNew(View view) {


    }

    @Override
    public void onClick(View view) {


    }

    private void signIn(int a) {

    }


    private void register(String name, String lastname, String email, String mobile, String password) {
        HashMap<String, String> params = new HashMap<>();


        params.put("name", name);
        params.put("firstName", name);
        params.put("lastname", lastname);
        params.put("role_id", "1");
        params.put("firebasetoken", prefrenceManager.getKeyUserFirebasetoken());
        params.put("email", email);
        params.put("mobile", mobile);
        params.put("phoneNumber", mobile);
        params.put("phone", mobile);
        params.put("password", password);


        HashMap<String, String> reg = new HashMap<>();
        reg.put("firstName", name);
        reg.put("lastName", lastname);
        reg.put("role_id", "2");
        if (prefrenceManager.getKeyUserFirebasetoken() != null) {
            reg.put("firebasetoken", prefrenceManager.getKeyUserFirebasetoken());
        } else {
            reg.put("firebasetoken", "null");

        }
        reg.put("email", email);
        reg.put("mobile", mobile);
        reg.put("phoneNumber", mobile);
        reg.put("password", password);


        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Signing in....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();

        Request.Companion.postRequest(ApiConstants.Companion.getSignAuth(), reg, null,
                new RequestListener() {
                    @Override
                    public void onError(ANError error) {
                        Log.d("respp", error.toString());

                        Log.d("regerr", error.toString());
                        progressDialog.dismiss();
                        // alertDialogDelete("Error Registering");
                        snack("Error occurred . Try again .");
                    }

                    @Override
                    public void onError(String errorss) {
                        Log.d("respp", errorss);

                        Log.d("regerr", errorss);
                        progressDialog.dismiss();

                        //alertDialogDelete("Error Registering");

                        String error = "Logging In was unsuccessful";
                        try {
                            JSONObject jsonObject = new JSONObject(errorss);
                            if (jsonObject.opt("errors") != null) {
                                JSONObject errors = jsonObject.getJSONObject("errors");

                                if (errors.opt("root") != null) {
                                    alertDialogDelete(errors.getString("root"));
                                } else if (errors.opt("email") != null) {
                                    alertDialogDelete(errors.getJSONArray("email").optString(0));
                                } else if (errors.opt("password") != null) {
                                    alertDialogDelete(errors.getJSONArray("password").optString(0));
                                } else if (errors.opt("phoneNumber") != null) {
                                    alertDialogDelete(errors.getJSONArray("phoneNumber").optString(0));
                                } else {
                                    alertDialogDelete(error);
                                }
                            }

                        } catch (Exception nm) {
                            nm.printStackTrace();
                            Log.d("logintagjj", nm.toString());

                            alertDialogDelete(error);
                        }


                        snack("Error occurred . Try again .");
                    }

                    @Override
                    public void onSuccess(String response) {

                        Log.d("respp", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (!jsonObject.getString("error").equals("true")) {
                                //fragment = new LoginFragment();
                                progressDialog.dismiss();
                                // popOutFragments();
                                // setUpView();
                                login(email, password);

                                //alertDialogDelete("succesfull");
                            } else {
                                String errr = "";
                                try {

                                    JSONObject jsonObject1 = jsonObject.getJSONObject("errors");
                                    JSONArray jsonArray = jsonObject1.getJSONArray("email");


                                    for (int a = 0; a > jsonArray.length(); a++) {
                                        errr = jsonArray.getString(a);
                                    }

                                } catch (Exception nm) {
                                    nm.printStackTrace();
                                }
                                progressDialog.dismiss();
                                snack("Error occurred ." + errr);
                                //alertDialogDelete(jsonObject.getString("message"));
                            }
                        } catch (JSONException e) {
                            progressDialog.dismiss();
                            e.printStackTrace();
                            Log.d("signupexc", e.toString());
                        }

                    }
                });



    }

    private void alertDialogDelete(final String message) {
        final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //popOutFragments();
                        dialog.dismiss();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();

                        break;
                }
            }
        };


        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

        builder.setMessage(message).setPositiveButton("Okay", dialogClickListener)
                //.setNegativeButton("No", dialogClickListener)
                .show();

    }


    private void login(String email, String password) {

        NetworkUtils networkUtils = new NetworkUtils(LoginActivity.this);

        if (networkUtils.isNetworkAvailable()) {


            MaterialDialog.Builder builder = new MaterialDialog.Builder(LoginActivity.this)
                    .title("Verifying you")
                    .content("Please Wait")
                    .cancelable(false)
                    .progress(true, 0);


            dialog = builder.build();
            dialog.show();

            PrefManager prefManager = new PrefManager(LoginActivity.this);

            String url = ApiConstants.Companion.getLoginAuth();

            HashMap<String, String> params = new HashMap<>();
            params.put("mobile", email);
            params.put("email", email);
            params.put("password", password);
            params.put("user", "PHOTOZURI");
            params.put("role", "2");

            Log.d("logintag", params.toString());

            Request.Companion.postRequest(url, params, null, new RequestListener() {
                @Override
                public void onError(ANError error) {
                    Log.d("logintag", error.toString());
                    dialog.dismiss();
                    prefManager.setIsLoggedIn(false);

                }

                @Override
                public void onError(String errorss) {
                    Log.d("logintagjj", errorss);
                    dialog.dismiss();

                    String error = "Logging In was unsuccessful";
                    try {
                        JSONObject jsonObject = new JSONObject(errorss);
                        if (jsonObject.opt("errors") != null) {
                            JSONObject errors = jsonObject.getJSONObject("errors");

                            if (errors.opt("root") != null) {
                                alertDialogDelete(errors.getString("root"));
                            } else if (errors.opt("email") != null) {
                                alertDialogDelete(errors.getJSONArray("email").optString(0));
                            } else if (errors.opt("password") != null) {
                                alertDialogDelete(errors.getJSONArray("password").optString(0));
                            } else {
                                alertDialogDelete(error);
                            }
                        }

                    } catch (Exception nm) {
                        nm.printStackTrace();
                        Log.d("logintagjj", nm.toString());

                        alertDialogDelete(error);
                    }


                    //alertDialogDelete("Login error ..Try again ");
                    prefManager.setIsLoggedIn(false);

                }

                @Override
                public void onSuccess(String response) {
                    Log.d("logintag", response);
                    if (dialog != null) {
                        dialog.dismiss();
                    }

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getString("error").equals("false") || !jsonObject.getBoolean("error")) {
                            UserModel userModel = UserModelParse.getUser(response, prefManager.getISV1());

                            if (prefrenceManager.getKeyUserFirebasetoken().equals(userModel.getFirebasetoken())) {

                            } else {
                                try {
                                    if (prefrenceManager != null && prefrenceManager.getKeyUserFirebasetoken() != null && !"null".equals(prefrenceManager.getKeyUserFirebasetoken())) {
                                        Request.Companion.updateFire(prefrenceManager.getKeyUserFirebasetoken(), String.valueOf(userModel.getUserId()), "");
                                    }
                                } catch (Exception nm) {
                                    nm.printStackTrace();
                                }
                                userModel.setFirebasetoken(prefrenceManager.getKeyUserFirebasetoken());
                            }

                            if (goooglePhoto != null) {
                                userModel.setUriPhoto(Uri.parse(goooglePhoto));
                            }

                            prefManager.setUserData(userModel);
                            prefManager.setIsLoggedIn(true);


                            startActivity(new Intent(LoginActivity.this, MainActivity.class));


                            finish();

                        } else {
                            prefManager.setIsLoggedIn(false);

                            dialog.dismiss();
                            String error = "Logging In was unsuccessful";
                            try {
                                if (jsonObject.getString("errors") != null) {
                                    JSONObject errors = jsonObject.getJSONObject("errors");

                                    if (errors.getString("root") != null) {
                                        alertDialogDelete(errors.getString("root"));
                                    } else if (errors.getString("email") != null) {
                                        alertDialogDelete(errors.optString("email"));
                                    } else if (errors.getString("password") != null) {
                                        alertDialogDelete(errors.getString("password"));
                                    } else {
                                        alertDialogDelete(error);
                                    }
                                }

                            } catch (Exception nm) {
                                nm.printStackTrace();
                            }


                        }
                    } catch (JSONException j) {
                        prefManager.setIsLoggedIn(false);
                        alertDialogDelete("Logging In was unsuccessful");

                        Log.d("logingerr", j.toString() + "  " + response);
                        dialog.dismiss();
                    }


                }
            });


        } else {
            snack("Check your Internet Connection");

        }

    }


    public void forgotPassword(View view) {
        changeView(3);
    }

    public void submitEmail(View view) {
        EditText editText = findViewById(R.id.registered_emailid);
        if (TextUtils.isEmpty(editText.getText().toString())) {
            snack("Enter your email address");
            return;
        }
        if (isValidEmail(editText.getText().toString())) {
            forgotPasswordCall(editText.getText().toString());
        } else {
            snack("Enter a valid email");
            editText.requestFocus();
            editText.setError("Invalid email");
        }


    }

    private void forgotPasswordCall(String s) {
        HashMap<String, String> param = new HashMap<>();

//        // param.put("apikey", prefrenceManager.getC_apikey());
//        param.put("_debug", "0");
        param.put("email", s);
//        param.put("_apikey", "");
//        param.put("_esapikey", ApiConstants.ESAPIKEY);
//        param.put("_apty", "caa-main");
//        param.put("_apir", "");

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Working....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();


        Request.Companion.postRequest(ApiConstants.Companion.getPasswordAuth(), param, "", new RequestListener() {
            @Override
            public void onError(ANError error) {
                //  Log.d("forgotpass", error.toString());

                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                //  alertDialog("Error occurred . Please try again ");

            }

            @Override
            public void onError(String errorss) {
                // Log.d("forgotpass", error);

                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }


                try {
                    JSONObject jsonObject = new JSONObject(errorss);
                    if (!jsonObject.getBoolean("error")) {
                        String res = jsonObject.getString("data");
                        alertDialogDelete(res);

                        changeView(2);

                    } else {


                        JSONObject errors = jsonObject.getJSONObject("errors");
                        JSONArray error = errors.getJSONArray("email");
                        snack(error.optString(0));
                        alertDialogDelete(error.optString(0));

                    }


                } catch (JSONException e) {
                    Log.d("forgotpassddd", e.toString());
                    e.printStackTrace();
                }

            }

            @Override
            public void onSuccess(String response) {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.d("forgotpass", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (!jsonObject.getBoolean("error")) {
                        String res = jsonObject.getString("data");
                        alertDialogDelete(res);

                        changeView(2);

                    } else {


                        JSONObject errors = jsonObject.getJSONObject("errors");

                        JSONArray error = errors.getJSONArray("email");
                        snack(error.optString(0));
                        alertDialogDelete(error.optString(0));


                        //  alertDialog("" + jsonObject.getString("srm"));
                    }


                } catch (JSONException e) {
                    Log.d("forgotpassddd", e.toString());
                    //alertDialog("Error occurred . Please try again ");
                    e.printStackTrace();
                }

            }
        });

    }

    class CustomListener implements View.OnClickListener {
        private final Dialog dialog;
        String names, lastname, email, pass;
        private Button button;
        private int position;

        public CustomListener(Dialog dialog, Button theButton, String names, String lastname, String email, String pass) {
            this.dialog = dialog;
            this.button = theButton;
            this.names = names;
            this.lastname = lastname;
            this.email = email;
            this.pass = pass;


        }

        @Override
        public void onClick(View v) {
            TextInputEditText edtCaption = null;

            // edtCaption = dialog.findViewById(R.id.edt_phone);


            String caption = "";

            if (TextUtils.isEmpty(edtCaption.getText().toString())
                    && isValidPhoneNumber(edtCaption.getText().toString())) {
                edtCaption.setError("Valid phone Required");
            } else {

                register(names, lastname, email, edtCaption.getText().toString(), pass);
                dialog.dismiss();
            }

            caption = edtCaption.getText().toString();


        }

    }


}
