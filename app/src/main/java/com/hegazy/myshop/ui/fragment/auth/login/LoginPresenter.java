package com.hegazy.myshop.ui.fragment.auth.login;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.loginModel.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;

public class LoginPresenter {

    ChickStatusInterface loginInterface;

    public LoginPresenter(ChickStatusInterface loginInterface) {
        this.loginInterface = loginInterface;
    }


    public void loginUser(String email , String password ){

        loginInterface.showProgress(R.string.please_wiht);

        getClient().loginUser(email,password).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {


                try {

                    if (response.body().getStatus() == true)
                    {
                        loginInterface.onSuccess(response.body().getMessage());
                        loginInterface.saveDataUser(response.body().getData());
                        loginInterface.saveTokenUser(response.body().getData().getToken());
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
            public void onFailure(Call<LoginModel> call, Throwable t) {
                loginInterface.onFailure(t.getMessage());

            }
        });

    }



}
