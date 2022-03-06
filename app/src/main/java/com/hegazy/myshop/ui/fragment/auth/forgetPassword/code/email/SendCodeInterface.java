package com.hegazy.myshop.ui.fragment.auth.forgetPassword.code.email;

public interface SendCodeInterface {

    void onSuccess(String message);

    void onError(String mass);

    void onFailure(String mass);

    void showProgress(int title);


}
