package com.dev.foodmovers.Network;


public interface ResponseCallback {
    void response(ResponseModel responseModel);

    void response(ResponseObject responseModel);
}
