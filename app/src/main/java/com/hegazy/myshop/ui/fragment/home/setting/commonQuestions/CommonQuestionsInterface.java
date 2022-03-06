package com.hegazy.myshop.ui.fragment.home.setting.commonQuestions;

import com.hegazy.myshop.data.model.categoriesModel.CategoriesModelDatum;
import com.hegazy.myshop.data.model.commonQuestionsModel.CommonQuestionsModelDatum;

import java.util.List;

public interface CommonQuestionsInterface {


    void getQuestions(List<CommonQuestionsModelDatum> QuestionsList);


    void showProgress(int title);

    void onSuccess(String massge);

    void onError(String mass);

    void onFailure(String mass);
}
