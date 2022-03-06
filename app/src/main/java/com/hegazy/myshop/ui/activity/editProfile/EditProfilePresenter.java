package com.hegazy.myshop.ui.activity.editProfile;

import com.google.gson.Gson;
import com.hegazy.myshop.data.model.updateProfileModel.UpdateProfileModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;

public class EditProfilePresenter {

    EditProfileInterface editProfileInterface;

    public EditProfilePresenter(EditProfileInterface editProfileInterface) {
        this.editProfileInterface = editProfileInterface;
    }


    public void Update(String token, String lang, String name, String phone, String email, MultipartBody.Part image) {

        editProfileInterface.showProgress("Pleas Whit");

        getClient().updateProfile(token,lang,name,phone,email,image).enqueue(new Callback<UpdateProfileModel>() {
            @Override
            public void onResponse(Call<UpdateProfileModel> call, Response<UpdateProfileModel> response) {


                    if (response.body().getStatus()==true) {
                        editProfileInterface.onSuccess(response.body().getMessage());

                    } else {

                        editProfileInterface.onError(response.body().getMessage());
                    }

            }

            @Override
            public void onFailure(Call<UpdateProfileModel> call, Throwable t) {

            }
        });
    }
}
