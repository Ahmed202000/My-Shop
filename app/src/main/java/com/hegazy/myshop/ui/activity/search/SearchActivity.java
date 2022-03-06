package com.hegazy.myshop.ui.activity.search;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hegazy.myshop.R;
import com.hegazy.myshop.adapter.SearchAdapter;
import com.hegazy.myshop.data.model.homeModel.HomeModelProduct;
import com.hegazy.myshop.data.model.searchProductModel.SearchProductModelDatum;
import com.hegazy.myshop.helpar.HelperMethod;
import com.hegazy.myshop.ui.activity.HomeActivity;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hegazy.myshop.data.locale.Const.USER_TOKEN;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.LoadData;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;

public class SearchActivity extends AppCompatActivity implements SearchInterface {

    @BindView(R.id.activity_search_iv_bake)
    ImageView activitySearchIvBake;
    @BindView(R.id.activity_search_tv_search)
    EditText activitySearchTvSearch;
    @BindView(R.id.activity_search_rv_product)
    RecyclerView activitySearchRvProduct;

    private ProgressDialog dialog;


    private List<SearchProductModelDatum> searchProductModelDatumList = new ArrayList<>();
    SearchAdapter searchAdapter;
    private GridLayoutManager gridLayoutManager;
    SearchPresenter searchPresenter;
    private String text;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        searchPresenter=new SearchPresenter(SearchActivity.this);


        TextWatcher search = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                intiProduct();
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        activitySearchTvSearch.addTextChangedListener(search);



    }



    private void intiProduct() {

        text=activitySearchTvSearch.getText().toString();
        token = LoadData(SearchActivity.this, USER_TOKEN);

        gridLayoutManager = new GridLayoutManager(SearchActivity.this, 2);
        activitySearchRvProduct.setLayoutManager(gridLayoutManager);
        searchPresenter.search(text,token);


    }



    @OnClick(R.id.activity_search_iv_bake)
    public void onViewClicked() {
        onBackPressed();
    }


    @Override
    public void getProductSearch(List<SearchProductModelDatum> productList) {

        searchProductModelDatumList.addAll(productList);
        searchAdapter=new SearchAdapter(this,SearchActivity.this,searchProductModelDatumList);
        activitySearchRvProduct.setAdapter(searchAdapter);
        searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress(int title) {
        dialog= showProgressDialog(this,getString(title));
        dialog.show();
    }

    @Override
    public void onSuccess(String massge) {
        MDToast.makeText(this, massge, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();

        dialog.cancel();

    }

    @Override
    public void onError(String mass) {
        MDToast.makeText(this, mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();

        dialog.cancel();
    }

    @Override
    public void onFailure(String mass) {
        MDToast.makeText(this, mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();
        dialog.cancel();

    }
}