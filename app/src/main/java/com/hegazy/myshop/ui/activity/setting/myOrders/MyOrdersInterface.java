package com.hegazy.myshop.ui.activity.setting.myOrders;


import com.hegazy.myshop.data.model.getAddressModel.GetAddressModelDatum;
import com.hegazy.myshop.data.model.myOrderModel.MyOrderModelDatum;

import java.util.List;

public interface MyOrdersInterface {


    void getMyOrders(List<MyOrderModelDatum> ordersList);


    void showProgress(String title);

    void onSuccess(String message);

    void onError(String mass);

    void onFailure(String mass);
}
