package com.hegazy.myshop.ui.fragment.auth.forgetPassword.resete;

public interface ResetPasswordInterface {

    void onSuccess(String message);

    void onError(String mass);

    void onFailure(String mass);

    void showProgress(int title);


}
