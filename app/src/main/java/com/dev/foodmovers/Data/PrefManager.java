package com.dev.foodmovers.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import java.util.HashMap;

/**
 * Created by Eric on 1/5/2018.
 */

public class PrefManager {
    // Shared preferences file name
    private static final String PREF_NAME = "Odijo";
    // All Shared Preferences Keys
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_ACCOUNT_NAME = "key_googleAccount";

    private static final String KEY_USER_ID = "UserId";
    private static final String KEY_USER_NAME = "UserName";
    private static final String KEY_USER_F_NAME = "FirstName";
    private static final String KEY_USER_L_NAME = "LastName";
    private static final String KEY_USER_EMAIL = "Email";
    private static final String KEY_USER_PHONENumber = "PhoneNumber";
    private static final String KEY_USER_Designation = "Designation";
    private static final String KEY_USER_USERTYPE = "UserType";
    private static final String KEY_USER_PHOTO = "Photo";
    private static final String KEY_USER_URIPHOTO = "UriPhoto";
    private static final String KEY_USER_FIREBASETOKEN = "firebasetoken";

    private static final String KEY_USER_FB_ID = "fbid";
    private static final String KEY_USER_FB_TOKEN = "fbtoken";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_ISV1 = "isv1";

    private static final String PICCASA_WEB_USERID = "picassauserid";
    private static final String googleAuthKey = "googleAuth";
    private static final String googleAuthConnected = "googleAuthConnected";


    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;


    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.commit();
    }

    public String getFbId() {
        return pref.getString(KEY_USER_FB_ID, "null");

    }

    public String getKeyUserFbToken() {
        return pref.getString(KEY_USER_FB_TOKEN, "null");
    }

    public String getPiccasaWebUserid() {
        return pref.getString(PICCASA_WEB_USERID, "null");
    }

    public void setPiccasaWebUserid(String userid) {
        editor.putString(PICCASA_WEB_USERID, userid);
        editor.commit();
    }

    public boolean isGoogleAuthConnected() {
        return pref.getBoolean(googleAuthConnected, true);
    }

    public void setGoogleAuthConnected(boolean authConnected) {
        editor.putBoolean(googleAuthConnected, authConnected);
        editor.commit();
    }

    public boolean getISV1() {
        return pref.getBoolean(KEY_ISV1, true);
    }

    public void setISV1(boolean isv1) {
        editor.putBoolean(KEY_ISV1, isv1);
        editor.commit();
    }

    public UserModel getUserData() {
        UserModel userData = new UserModel();
        userData.setUserId(pref.getInt(KEY_USER_ID, 0));
        userData.setUserName(pref.getString(KEY_USER_NAME, "NULL"));
        userData.setFirstName(pref.getString(KEY_USER_F_NAME, "NULL"));
        userData.setLastName(pref.getString(KEY_USER_L_NAME, "NULL"));
        userData.setEmail(pref.getString(KEY_USER_EMAIL, "NULL"));
        userData.setPhoneNumber(pref.getString(KEY_USER_PHONENumber, "NULL"));
        userData.setUriPhoto(Uri.parse(pref.getString(KEY_USER_URIPHOTO, "NULL")));
        userData.setToken(pref.getString(KEY_TOKEN, null));
        // userData.setDesignation(pref.getString(KEY_USER_Designation, "NULL"));
        // userData.setUserType(pref.getInt(KEY_USER_USERTYPE, 0));
        userData.setPhoto(pref.getString(KEY_USER_PHOTO, "NULL"));


        return userData;
    }

    ///SET AND GET USER DATA
    public void setUserData(UserModel userData) {


        editor.putInt(KEY_USER_ID, userData.getUserId());
        editor.putString(KEY_USER_NAME, userData.getUserName());
        editor.putString(KEY_USER_F_NAME, userData.getFirstName());
        editor.putString(KEY_USER_L_NAME, userData.getLastName());
        editor.putString(KEY_USER_EMAIL, userData.getEmail());
        editor.putString(KEY_USER_PHONENumber, userData.getPhoneNumber());
        editor.putString(KEY_TOKEN, userData.getToken());
        try {
            if (userData.getUriPhoto() != null) {
                editor.putString(KEY_USER_URIPHOTO, userData.getUriPhoto().toString());
            } else {
                editor.putString(KEY_USER_URIPHOTO, "");
            }
            //editor.putString(KEY_USER_Designation, userData.getDesignation());
            // editor.putInt(KEY_USER_USERTYPE, userData.getUserType());
            editor.putString(KEY_USER_PHOTO, userData.getPhoto());


        } catch (Exception nm) {
            nm.printStackTrace();
        }
        editor.commit();

    }

    public String getKeyUserFirebasetoken() {
        return pref.getString(KEY_USER_FIREBASETOKEN, "null");
    }

    public void setKeyUserFirebasetoken(String token) {
        editor.putString(KEY_USER_FIREBASETOKEN, token);

        editor.commit();
    }

    public String getGoogleAuth() {
        return pref.getString(googleAuthKey, "null");
    }

    public void setGoogleAuth(String auth) {
        editor.putString(googleAuthKey, auth);

        editor.commit();
    }

    public HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        // headers.put("Accept", "multipart/form-data");
        // headers.put("Content-Type", "application/json");
        headers.put("Content-Type", "text/plain");
        //headers.put("Content-Length", "302");
        //headers.put("Authorization", "Bearer " + pref.getString(KEY_TOKEN, "NULL"));
        //headers.put("Authorization", "Bearer " + Preferences.getInstance().getAccessToken());

        return headers;
    }

    public String getToken() {
        return pref.getString(KEY_TOKEN, "o");
    }

    public String getGoogleAccountName() {
        return pref.getString(KEY_ACCOUNT_NAME, "null");
    }

    public void setGoogleAccountName(String accountName) {
        editor.putString(KEY_ACCOUNT_NAME, accountName);
        editor.commit();
    }

    public void clearData() {
        //editor.clear();
        editor.putBoolean(KEY_IS_LOGGED_IN, false);
        editor.putBoolean(KEY_ISV1, true);


        editor.commit();
    }
}
