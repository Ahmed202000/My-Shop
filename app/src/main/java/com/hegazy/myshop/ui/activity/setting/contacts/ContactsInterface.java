package com.hegazy.myshop.ui.activity.setting.contacts;

import com.hegazy.myshop.data.model.categoryProductsModel.CategoryProductsModelDatum;
import com.hegazy.myshop.data.model.socialMedia.SocialMediaDatum;

import java.util.List;

public interface ContactsInterface {


    void getSocialMedia(List<SocialMediaDatum> productList);


    void showProgress(int title);

    void onSuccess(String massge);

    void onError(String mass);

    void onFailure(String mass);
}
