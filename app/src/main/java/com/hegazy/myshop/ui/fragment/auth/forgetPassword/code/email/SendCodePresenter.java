package com.hegazy.myshop.ui.fragment.auth.forgetPassword.code.email;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.sendCodeModel.SendCodeModel;
import com.hegazy.myshop.data.model.sendEmailModel.SendEmailModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;

public class SendCodePresenter {

    SendCodeInterface sendCodeInterface;

    public SendCodePresenter(SendCodeInterface sendCodeInterface) {
        this.sendCodeInterface = sendCodeInterface;
    }



    public void forgotPasswordSendCode(String email,String lang,String code)
    {



        sendCodeInterface.showProgress(R.string.please_wiht);

            getClient().sendCodeModel(lang,email,code).enqueue(new Callback<SendCodeModel>() {
                @Override
                public void onResponse(Call<SendCodeModel> call, Response<SendCodeModel> response) {

                    if (response.body().status==true) {

                        sendCodeInterface.onSuccess(response.body().message);
                    }
                    else
                    {
                        sendCodeInterface.onError(response.body().message);

                    }
                }

                @Override
                public void onFailure(Call<SendCodeModel> call, Throwable t) {

                }
            });

        }
    }


