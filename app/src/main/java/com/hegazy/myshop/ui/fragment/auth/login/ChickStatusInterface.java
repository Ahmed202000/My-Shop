package com.hegazy.myshop.ui.fragment.auth.login;

import com.hegazy.myshop.data.model.loginModel.LoginModelData;

import java.util.List;

public interface ChickStatusInterface {

    void onSuccess(String massge);

    void onError(String mass);

    void onFailure(String mass);

    void saveDataUser(LoginModelData loginModelData);
    void saveTokenUser(String token);


    void showProgress(int title);


}
