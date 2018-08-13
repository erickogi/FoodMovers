package com.dev.foodmovers.Views.Login;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.dev.foodmovers.Data.PrefManager;
import com.dev.foodmovers.MainActivity;
import com.dev.foodmovers.R;

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

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();

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
