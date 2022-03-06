package com.hegazy.myshop.ui.fragment.home.setting.addComplaint;

public interface AddComplaintInterface {

    void showProgress(int title);

    void onSuccess(String massge);

    void onError(String mass);

    void onFailure(String mass);

}
