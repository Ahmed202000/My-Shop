package com.hegazy.myshop.ui.fragment.home.setting.commonQuestions;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hegazy.myshop.R;
import com.hegazy.myshop.adapter.CategoriesAdapter;
import com.hegazy.myshop.adapter.CommonQuestionsAdapter;
import com.hegazy.myshop.data.model.categoriesModel.CategoriesModelDatum;
import com.hegazy.myshop.data.model.commonQuestionsModel.CommonQuestionsModelDatum;
import com.hegazy.myshop.ui.fragment.home.setting.categories.CategoiseActivity;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;

public class CommonQuestionsActivity extends AppCompatActivity implements CommonQuestionsInterface{

    @BindView(R.id.activity_categories_iv_bake)
    ImageView activityCategoriesIvBake;
    @BindView(R.id.activity_common_questions_rv_questions)
    RecyclerView activityCommonQuestionsRvQuestions;

    private List<CommonQuestionsModelDatum> commonQuestionsModelDatumList = new ArrayList<>();
    CommonQuestionsAdapter commonQuestionsAdapter;
    LinearLayoutManager linearLayoutManager;
    private ProgressDialog dialog;
    private CommonQuestionsPresenter commonQuestionsPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_questions);
        ButterKnife.bind(this);

        commonQuestionsPresenter=new CommonQuestionsPresenter(this);
        intiQuestions();

    }



    private void intiQuestions() {

        linearLayoutManager=new LinearLayoutManager(CommonQuestionsActivity.this);
        activityCommonQuestionsRvQuestions.setLayoutManager(linearLayoutManager);
        commonQuestionsPresenter.CommonQuestions();
    }


    @Override
    public void getQuestions(List<CommonQuestionsModelDatum> QuestionsList) {

        commonQuestionsModelDatumList.addAll(QuestionsList);
        commonQuestionsAdapter=new CommonQuestionsAdapter(this,commonQuestionsModelDatumList);
        activityCommonQuestionsRvQuestions.setAdapter(commonQuestionsAdapter);
        commonQuestionsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress(int title) {
        dialog = showProgressDialog(this, getString(title));
        dialog.show();
    }
    @Override
    public void onSuccess(String massge) {
        // hide dialog
        dialog.cancel();
        MDToast.makeText(this, massge, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();

    }

    @Override
    public void onError(String mass) {
        // hide dialog
        dialog.cancel();
        MDToast.makeText(this, mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();

    }

    @Override
    public void onFailure(String mass) {
        MDToast.makeText(this, mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();
        dialog.cancel();
    }





    @OnClick(R.id.activity_categories_iv_bake)
    public void onViewClicked() {
        onBackPressed();
        finish();
    }
}