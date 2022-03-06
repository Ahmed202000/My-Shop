package com.hegazy.myshop.ui.fragment.home.notification;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.homeModel.HomeModel;
import com.hegazy.myshop.data.model.notificationsModel.NotificationsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;

public class NotificationsPresenter {

    NotificationsInterface notificationsInterface;

    public NotificationsPresenter(NotificationsInterface notificationsInterface) {
        this.notificationsInterface = notificationsInterface;
    }


    public void Notifications(String token){

        notificationsInterface.showProgress(R.string.please_wiht);

        getClient().getNotifications(token,changeLang()).enqueue(new Callback<NotificationsModel>() {
            @Override
            public void onResponse(Call<NotificationsModel> call, Response<NotificationsModel> response) {

                try {

                    if (response.body().getStatus() == true)
                    {
                        notificationsInterface.onSuccess(response.body().getMessage());
                        notificationsInterface.getNotifications(response.body().getData().getData());
                    }

                    else if (response.body().getStatus() == false)
                    {

                        notificationsInterface.onError(response.body().getMessage());

                    }
                }
                catch(Exception e)
                {
                }

            }

            @Override
            public void onFailure(Call<NotificationsModel> call, Throwable t) {
                notificationsInterface.onFailure(t.getMessage());
            }
        });

    }




}
