package com.hegazy.myshop.ui.fragment.auth.forgetPassword.email;

import com.hegazy.myshop.data.model.loginModel.LoginModelData;

public interface SendEmailInterface {

    void onSuccess(String message);

    void onError(String mass);

    void onFailure(String mass);

    void showProgress(int title);


}
