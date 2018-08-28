package com.dev.foodmovers.Kogi.Utils;


import com.dev.foodmovers.Network.ResponseModel;
import com.dev.foodmovers.Network.ResponseObject;

public interface ResponseCallback {
    void response(ResponseModel responseModel);

    void response(ResponseObject responseModel);
}
