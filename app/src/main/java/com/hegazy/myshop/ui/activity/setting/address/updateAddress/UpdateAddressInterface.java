package com.hegazy.myshop.ui.activity.setting.address.updateAddress;

public interface UpdateAddressInterface {

    void showProgress(int title);

    void onSuccess(String message);

    void onError(String mass);

    void onFailure(String mass);

}
