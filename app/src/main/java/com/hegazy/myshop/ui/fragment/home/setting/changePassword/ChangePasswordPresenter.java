package com.hegazy.myshop.ui.fragment.home.setting.changePassword;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.sendEmailModel.SendEmailModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;

public class ChangePasswordPresenter {

    ChangePasswordInterface resetPasswordInterface;

    public ChangePasswordPresenter(ChangePasswordInterface resetPasswordInterface) {
        this.resetPasswordInterface = resetPasswordInterface;
    }



    public void changPassword(String token,String lang,String oldPassword,String newPassword)
    {



        resetPasswordInterface.showProgress(R.string.please_wiht);

            getClient().changePasswordModel(token,lang,oldPassword,newPassword).enqueue(new Callback<SendEmailModel>() {
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


