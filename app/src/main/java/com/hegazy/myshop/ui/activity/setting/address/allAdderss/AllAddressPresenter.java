package com.hegazy.myshop.ui.activity.setting.address.allAdderss;

import com.hegazy.myshop.data.model.getAddressModel.GetAddressModel;
import com.hegazy.myshop.data.model.socialMedia.SocialMedia;
import com.hegazy.myshop.ui.activity.setting.contacts.ContactsInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;

public class AllAddressPresenter {

    AllAddressInterface allAddressInterface;


    public AllAddressPresenter(AllAddressInterface allAddressInterface) {
        this.allAddressInterface = allAddressInterface;
    }

    public void getAddress(String token,String lang)
    {

        allAddressInterface.showProgress("Pleas Whit");

        getClient().getAddress(token,lang).enqueue(new Callback<GetAddressModel>() {
            @Override
            public void onResponse(Call<GetAddressModel> call, Response<GetAddressModel> response) {

                try {

                    if (response.body().getStatus()==true) {

                        allAddressInterface.onSuccess(response.body().getMessage());
                        allAddressInterface.GetAddress(response.body().getData().getData());
                    }
                    else
                    {
                        allAddressInterface.onError(response.body().getMessage());

                    }

                }
                catch (Exception e)
                {

                }
            }

            @Override
            public void onFailure(Call<GetAddressModel> call, Throwable t) {

            }
        });
    }
}
