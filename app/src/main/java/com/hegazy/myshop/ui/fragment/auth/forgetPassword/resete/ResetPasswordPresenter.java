package com.hegazy.myshop.ui.fragment.auth.forgetPassword.resete;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.sendEmailModel.SendEmailModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;

public class ResetPasswordPresenter {

    ResetPasswordInterface resetPasswordInterface;

    public ResetPasswordPresenter(ResetPasswordInterface resetPasswordInterface) {
        this.resetPasswordInterface = resetPasswordInterface;
    }



    public void resetPassword(String email,String lang,String code,String password)
    {



        resetPasswordInterface.showProgress(R.string.please_wiht);

            getClient().resetPasswordModel(lang,email,code,password).enqueue(new Callback<SendEmailModel>() {
                @Override
                public void onResponse(Call<SendEmailModel> call, Response<SendEmailModel> response) {

                    if (response.body().status==true) {

                        resetPasswordInterface.onSuccess(response.body().message);
                    }
                    else
                    {
                        resetPasswordInterface.onError(response.body().message);

                    }
                }

                @Override
                public void onFailure(Call<SendEmailModel> call, Throwable t) {

                }
            });

        }
    }


