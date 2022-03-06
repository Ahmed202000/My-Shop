package com.hegazy.myshop.ui.activity.setting.contacts;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.socialMedia.SocialMedia;
import com.hegazy.myshop.helpar.HelperMethod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;

public class ContactsPresenter {

    ContactsInterface contactsInterface;

    public ContactsPresenter(ContactsInterface contactsInterface) {
        this.contactsInterface = contactsInterface;
    }

    public void getContacts()
    {

        contactsInterface.showProgress(R.string.please_wiht);

        getClient().socialMedia(HelperMethod.changeLang()).enqueue(new Callback<SocialMedia>() {
            @Override
            public void onResponse(Call<SocialMedia> call, Response<SocialMedia> response) {

                try {

                    if (response.body().getStatus()==true) {

                        contactsInterface.onSuccess(response.body().getMessage());
                        contactsInterface.getSocialMedia(response.body().getData().getData());
                    }
                    else
                    {
                        contactsInterface.onError(response.body().getMessage());

                    }

                }
                catch (Exception e)
                {

                }
            }

            @Override
            public void onFailure(Call<SocialMedia> call, Throwable t) {

            }
        });
    }
}
