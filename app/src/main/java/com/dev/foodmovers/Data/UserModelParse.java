package com.dev.foodmovers.Data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Eric on 1/26/2018.
 */

public class UserModelParse {
//    {
//        "error": false,
//            "profile": {
//        "id": 1,
//                "name": "Eric kogi",
//                "email": "erickogi14@gmail.com",
//                "mobile": "0714406984",
//                "apikey": "63bd9dd81d0135e67c1a131810f8129b",
//                "image": "http://www.erickogi.co.ke/photozuri/uploads/0714406984.png",
//                "password": "700c8b805a3e2a265b01c77614cd8b21"
//    }

//    {
//        "data": {
//        "id": 1,
//                "firstname": "John",
//                "lastname": "Wanyoike",
//                "email": "jhnwanyoike@gmail.com",
//                "phonenumber": null,
//                "firebasetoken": null,
//                "role_id": "1",
//                "status": "1",
//                "avatar": "/15a6f7f7e8cb68.jpg",
//                "created_at": "2017-09-12 15:28:55",
//                "updated_at": "2018-03-27 17:02:59",
//                "full_name": "John Wanyoike",
//                "alink": "https://www.photozuri.com/15a6f7f7e8cb68.jpg"
//    },
//        "meta": {
//        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEsImlzcyI6Imh0dHA6Ly9kYXNoYm9hcmQucGhvdG96dXJpLmNvbS9hcGkvYXV0aC9sb2dpbiIsImlhdCI6MTUyMzk5MDAzMSwiZXhwIjo1MzQzMTMzODEwMTEsIm5iZiI6MTUyMzk5MDAzMSwianRpIjoiRThKN0FtSjUyUkRsWHBDQiJ9.oWfnUwD_cSCB6wWSvDvV12oaVE576dSmovf1kyYDMAE"
//    },
//        "error": false
//    }

    public static UserModel getUser(String response, boolean isv1) {
        UserModel userModel = new UserModel();

        if (isv1) {

            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(response);
                JSONObject jsonObject1 = jsonObject.getJSONObject("data");

                userModel.setUserId(jsonObject1.getInt("id"));

                userModel.setFirstName(jsonObject1.getString("firstname"));
                userModel.setLastName(jsonObject1.getString("lastname"));


                userModel.setUserName(jsonObject1.getString("full_name"));


                userModel.setEmail(jsonObject1.getString("email"));
                userModel.setPhoneNumber(jsonObject1.getString("phonenumber"));

                userModel.setPhoto(jsonObject1.getString("avatar"));
                userModel.setFirebasetoken(jsonObject1.getString("firebasetoken"));


                userModel.setRole_id(jsonObject1.getString("role_id"));

                userModel.setCreated_at(jsonObject1.getString("created_at"));
                userModel.setAlink(jsonObject1.getString("alink"));

                JSONObject jsonObjectmeta = jsonObject.getJSONObject("meta");
                userModel.setToken(jsonObjectmeta.getString("token"));


            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else {

            try {
                JSONObject jsonObject = new JSONObject(response);

                JSONObject jsonObject1 = jsonObject.getJSONObject("profile");
                userModel.setUserName(jsonObject1.getString("name"));
                userModel.setFirstName(userModel.getUserName());
                userModel.setLastName(userModel.getUserName());

                userModel.setEmail(jsonObject1.getString("email"));
                userModel.setPhoneNumber(jsonObject1.getString("mobile"));
                userModel.setUserId(jsonObject1.getInt("id"));
                userModel.setPhoto(jsonObject1.getString("image"));


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        return userModel;

    }
}
