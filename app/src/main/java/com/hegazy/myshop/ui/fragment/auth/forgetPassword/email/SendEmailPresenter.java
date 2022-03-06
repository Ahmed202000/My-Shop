package com.hegazy.myshop.ui.fragment.auth.forgetPassword.email;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.sendEmailModel.SendEmailModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;

public class SendEmailPresenter {

    SendEmailInterface sendEmailInterface;

    public SendEmailPresenter(SendEmailInterface sendEmailInterface) {
        this.sendEmailInterface = sendEmailInterface;
    }



    public void forgotPasswordSendEmail(String email,String lang)
    {



        sendEmailInterface.showProgress(R.string.please_wiht);

            getClient().sendEmailModel(lang,email).enqueue(new Callback<SendEmailModel>() {
                @Override
                public void onResponse(Call<SendEmailModel> call, Response<SendEmailModel> response) {

                    if (response.body().status==true) {

                        sendEmailInterface.onSuccess(response.body().message);
                    }
                    else
                    {
                        sendEmailInterface.onError(response.body().message);

                    }
                }

                @Override
                public void onFailure(Call<SendEmailModel> call, Throwable t) {

                }
            });

        }
    }


