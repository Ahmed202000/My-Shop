package com.hegazy.myshop.ui.fragment.order.validatePromoCode;

import com.hegazy.myshop.data.model.promoCodeModel.PromoCodeModelData;

public interface ValidatePromoCodeInterface {


    void showProgress(int title);

    void onSuccess(String message);

    void onError(String mass);

    void onFailure(String mass);

    void dataResponse(PromoCodeModelData data);

}
