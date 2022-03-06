package com.hegazy.myshop.ui.activity.setting.address.addAdderss;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.addAddressModel.AddAddressModel;
import com.hegazy.myshop.helpar.HelperMethod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;

public class AddAddressPresenter {



    AddAddressInterface addAddressInterface;

    public AddAddressPresenter(AddAddressInterface addAddressInterface) {
        this.addAddressInterface = addAddressInterface;
    }


    public void AddAddress(String token,String name,String  city,String region ,String details,double latitude,double longitude,String notes){

        addAddressInterface.showProgress(R.string.please_wiht);

        getClient().addAddress(token, HelperMethod.changeLang(),name,city,region,details,latitude,longitude,notes).enqueue(new Callback<AddAddressModel>() {
            @Override
            public void onResponse(Call<AddAddressModel> call, Response<AddAddressModel> response) {

                try {

                    if (response.body().getStatus() == true)
                    {
                        addAddressInterface.onSuccess(response.body().getMessage());
                    }

                    else if (response.body().getStatus() == false)
                    {

                        addAddressInterface.onError(response.body().getMessage());

                    }
                }
                catch(Exception e)
                {
                }

            }

            @Override
            public void onFailure(Call<AddAddressModel> call, Throwable t) {
                addAddressInterface.onFailure(t.getMessage());

            }
        });

    }



}
