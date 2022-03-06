package com.hegazy.myshop.ui.fragment.home.notification;

import com.hegazy.myshop.data.model.homeModel.HomeModelBanner;
import com.hegazy.myshop.data.model.homeModel.HomeModelProduct;
import com.hegazy.myshop.data.model.notificationsModel.NotificationsModelDatum;

import java.util.List;

public interface NotificationsInterface {


    void getNotifications(List<NotificationsModelDatum> notificationsList);


    void showProgress(int title);

    void onSuccess(String massge);

    void onError(String mass);

    void onFailure(String mass);
}
