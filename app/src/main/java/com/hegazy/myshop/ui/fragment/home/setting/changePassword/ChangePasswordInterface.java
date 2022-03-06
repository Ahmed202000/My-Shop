package com.hegazy.myshop.ui.fragment.home.setting.changePassword;

public interface ChangePasswordInterface {

    void onSuccess(String message);

    void onError(String mass);

    void onFailure(String mass);

    void showProgress(int title);


}
