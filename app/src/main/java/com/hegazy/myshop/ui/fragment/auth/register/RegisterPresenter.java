package com.hegazy.myshop.ui.fragment.auth.register;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.registerModel.RegisterModel;
import com.hegazy.myshop.ui.fragment.auth.login.ChickStatusInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;

public class RegisterPresenter {

    ChickStatusInterface loginInterface;

    public RegisterPresenter(ChickStatusInterface loginInterface) {
        this.loginInterface = loginInterface;
    }


    public void registerUser(String name,String phone,String email , String password ){

        loginInterface.showProgress(R.string.please_wiht);

        getClient().registerUser(changeLang(),name,phone,email,password).enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {


                try {

                    if (response.body().getStatus() == true)
                    {
                        loginInterface.onSuccess(response.body().getMessage());
                    }

                    else if (response.body().getStatus() == false)
                    {

                        loginInterface.onError(response.body().getMessage());

                    }
                }
                    catch(Exception e)
                    {
                    }

            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {

            }
        });

    }



}
