package com.hegazy.myshop.ui.activity.setting.address.allAdderss;


import com.hegazy.myshop.data.model.getAddressModel.GetAddressModelDatum;

import java.util.List;

public interface AllAddressInterface {


    void GetAddress(List<GetAddressModelDatum> getAddressModelDatumList);


    void showProgress(String title);

    void onSuccess(String massge);

    void onError(String mass);

    void onFailure(String mass);
}
