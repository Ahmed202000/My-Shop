package com.hegazy.myshop.ui.activity.setting.setting;

public interface SettingInterface {

    void showProgress(int title);

    void onSuccess(String massge);

    void onError(String mass);

    void onFailure(String mass);

    void data(String text);

}
