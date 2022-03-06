package com.hegazy.myshop.ui.fragment.home.setting.commonQuestions;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.categoriesModel.CategoriesModel;
import com.hegazy.myshop.data.model.commonQuestionsModel.CommonQuestionsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;

public class CommonQuestionsPresenter {

    CommonQuestionsInterface commonQuestionsInterface;

    public CommonQuestionsPresenter(CommonQuestionsInterface commonQuestionsInterface) {
        this.commonQuestionsInterface = commonQuestionsInterface;
    }


    public void CommonQuestions(){

        commonQuestionsInterface.showProgress(R.string.please_wiht);

        getClient().getQuestionsModel(changeLang()).enqueue(new Callback<CommonQuestionsModel>() {
            @Override
            public void onResponse(Call<CommonQuestionsModel> call, Response<CommonQuestionsModel> response) {

                try {

                    if (response.body().getStatus() == true)
                    {
                        commonQuestionsInterface.onSuccess(response.body().getMessage());
                        commonQuestionsInterface.getQuestions(response.body().getData().getData());
                    }

                    else if (response.body().getStatus() == false)
                    {

                        commonQuestionsInterface.onError(response.body().getMessage());

                    }
                }
                catch(Exception e)
                {
                }

            }

            @Override
            public void onFailure(Call<CommonQuestionsModel> call, Throwable t) {
                commonQuestionsInterface.onFailure(t.getMessage());

            }
        });

    }



}
