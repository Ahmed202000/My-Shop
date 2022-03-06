package com.hegazy.myshop.ui.activity.setting.orderDetails;


import com.hegazy.myshop.data.model.myOrderModel.MyOrderModelDatum;
import com.hegazy.myshop.data.model.orderDetailsModel.OrderDetailsModel;
import com.hegazy.myshop.data.model.orderDetailsModel.OrderDetailsModelData;
import com.hegazy.myshop.data.model.orderDetailsModel.OrderDetailsModelProduct;

import java.util.List;

public interface OrderDetailsInterface {


    void Product(List<OrderDetailsModelProduct> getOrderProduct);


    void showProgress(String title);

    void onSuccess(String message);

    void onError(String mass);

    void onFailure(String mass);

    void responseData(OrderDetailsModelData data);
}
