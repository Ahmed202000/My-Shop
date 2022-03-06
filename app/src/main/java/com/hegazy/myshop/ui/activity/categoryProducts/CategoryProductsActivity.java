package com.hegazy.myshop.ui.activity.categoryProducts;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hegazy.myshop.R;
import com.hegazy.myshop.adapter.CategoryProductsAdapter;
import com.hegazy.myshop.adapter.SearchAdapter;
import com.hegazy.myshop.data.model.categoryProductsModel.CategoryProductsModelDatum;
import com.hegazy.myshop.data.model.searchProductModel.SearchProductModelDatum;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hegazy.myshop.data.locale.Const.USER_TOKEN;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.LoadData;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;

public class CategoryProductsActivity extends AppCompatActivity implements CategoryProductsInterface {


    @BindView(R.id.activity_category_products_iv_bake)
    ImageView activityCategoryProductsIvBake;
    @BindView(R.id.activity_category_products_rv_product)
    RecyclerView activityCategoryProductsRvProduct;
    private ProgressDialog dialog;


    private List<CategoryProductsModelDatum> categoryProductsModelDatumList = new ArrayList<>();
    CategoryProductsAdapter categoryProductsAdapter;

    private LinearLayoutManager linearLayoutManager;

    CategoryProductsPresenter categoryProductsPresenter;

    private String token;
    private int idCateg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_products);
        ButterKnife.bind(this);

        categoryProductsPresenter = new CategoryProductsPresenter(CategoryProductsActivity.this);

         idCateg=getIntent().getExtras().getInt("idCategory");
        intiProduct();

    }


    private void intiProduct() {

        token = LoadData(CategoryProductsActivity.this, USER_TOKEN);

        linearLayoutManager = new LinearLayoutManager(CategoryProductsActivity.this);
        activityCategoryProductsRvProduct.setLayoutManager(linearLayoutManager);
        categoryProductsPresenter.categoryProducts(token,idCateg);


    }


    @Override
    public void getProductCategory(List<CategoryProductsModelDatum> productList) {
        categoryProductsModelDatumList.addAll(productList);
        categoryProductsAdapter = new CategoryProductsAdapter(this,this, categoryProductsModelDatumList);
        activityCategoryProductsRvProduct.setAdapter(categoryProductsAdapter);
        categoryProductsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress(int title) {
        dialog = showProgressDialog(this, this.getString(title));
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

    @OnClick(R.id.activity_category_products_iv_bake)
    public void onViewClicked() {
        onBackPressed();
    }
}