package com.hegazy.myshop.ui.activity.setting.setting;

import com.google.gson.Gson;
import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.settingModel.SettingModel;
import com.hegazy.myshop.data.model.updateProfileModel.UpdateProfileModel;
import com.hegazy.myshop.helpar.HelperMethod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;

public class SettingPresenter {

    SettingInterface settingInterface;

    public SettingPresenter(SettingInterface editProfileInterface) {
        this.settingInterface = editProfileInterface;
    }


    public void setting() {

        settingInterface.showProgress(R.string.please_wiht);

        getClient().setting(HelperMethod.changeLang()).enqueue(new Callback<SettingModel>() {
            @Override
            public void onResponse(Call<SettingModel> call, Response<SettingModel> response) {


                    if (response.isSuccessful()) {
                        settingInterface.onSuccess(response.body().getMessage());
                        settingInterface.data(response.body().getData().getAbout());

                    } else {

                        Gson gson=new Gson();
                        UpdateProfileModel errorResponse=new UpdateProfileModel();

                        try {

                            errorResponse=gson.fromJson(response.errorBody().string(),UpdateProfileModel.class);
                            settingInterface.onError(errorResponse.getMessage());


                        }
                        catch (Exception e)
                        {}

                    }

            }

            @Override
            public void onFailure(Call<SettingModel> call, Throwable t) {

            }
        });
    }
}
