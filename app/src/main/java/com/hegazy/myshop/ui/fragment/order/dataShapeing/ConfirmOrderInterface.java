package com.hegazy.myshop.ui.fragment.order.dataShapeing;

import com.hegazy.myshop.data.model.promoCodeModel.PromoCodeModelData;

public interface ConfirmOrderInterface {


    void showProgress(int title);

    void onSuccessOrder(String message);

    void onErrorOrder(String mass);

    void onFailureOrder(String mass);

}
