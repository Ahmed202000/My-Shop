package com.hegazy.myshop.ui.activity.setting.address.updateAddress;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.addAddressModel.AddAddressModel;
import com.hegazy.myshop.helpar.HelperMethod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;

public class UpdateAddressPresenter {



    UpdateAddressInterface updateAddressInterface;

    public UpdateAddressPresenter(UpdateAddressInterface updateAddressInterface) {
        this.updateAddressInterface = updateAddressInterface;
    }


    public void UpdateAddress(String token,int id,String name,String  city,String region ,String details,double latitude,double longitude,String notes){

        updateAddressInterface.showProgress(R.string.please_wiht);

        getClient().updateAddress(token, HelperMethod.changeLang(),id,name,city,region,details,latitude,longitude,notes).enqueue(new Callback<AddAddressModel>() {
            @Override
            public void onResponse(Call<AddAddressModel> call, Response<AddAddressModel> response) {

                try {

                    if (response.body().getStatus() == true)
                    {
                        updateAddressInterface.onSuccess(response.body().getMessage());
                    }

                    else if (response.body().getStatus() == false)
                    {

                        updateAddressInterface.onError(response.body().getMessage());

                    }
                }
                catch(Exception e)
                {
                }

            }

            @Override
            public void onFailure(Call<AddAddressModel> call, Throwable t) {
                updateAddressInterface.onFailure(t.getMessage());

            }
        });

    }



}
