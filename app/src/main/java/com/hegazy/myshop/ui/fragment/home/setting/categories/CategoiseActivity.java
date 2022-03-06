package com.hegazy.myshop.ui.fragment.home.setting.categories;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.data.AssetPathFetcher;
import com.hegazy.myshop.R;
import com.hegazy.myshop.adapter.CategoriesAdapter;
import com.hegazy.myshop.data.model.categoriesModel.CategoriesModelDatum;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;

public class CategoiseActivity extends AppCompatActivity implements CategoryInterface{


    @BindView(R.id.activity_categories_iv_bake)
    ImageView activityCategoriesIvBake;
    @BindView(R.id.activity_categories_rv_categories)
    RecyclerView activityCategoriesRvCategories;


    private List<CategoriesModelDatum> categoriesModelDataList = new ArrayList<>();
    CategoriesAdapter categoriesAdapter;
    LinearLayoutManager linearLayoutManager;

    CategoriesPresenter categoriesPresenter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoise);
        ButterKnife.bind(this);

        categoriesPresenter = new CategoriesPresenter(this);
        intiCategories();

    }




    private void intiCategories() {

        linearLayoutManager=new LinearLayoutManager(CategoiseActivity.this);
        activityCategoriesRvCategories.setLayoutManager(linearLayoutManager);
        categoriesPresenter.Category();
    }



    @Override
    public void getCategories(List<CategoriesModelDatum> categoryList) {

        categoriesModelDataList.addAll(categoryList);
        categoriesAdapter = new CategoriesAdapter(CategoiseActivity.this,CategoiseActivity.this, categoriesModelDataList);
        activityCategoriesRvCategories.setAdapter(categoriesAdapter);
        categoriesAdapter.notifyDataSetChanged();

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