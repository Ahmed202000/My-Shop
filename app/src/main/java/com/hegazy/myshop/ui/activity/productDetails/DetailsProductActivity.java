package com.hegazy.myshop.ui.activity.productDetails;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.hegazy.myshop.R;
import com.hegazy.myshop.adapter.ViewPagerImageProductAdapter;
import com.hegazy.myshop.data.model.addFavoritesModel.AddFavoritesModel;
import com.hegazy.myshop.data.model.addOrRemoveCartModel.AddOrRemoveCartModel;
import com.hegazy.myshop.data.model.productDetailsModel.ProductDetailsModelData;
import com.hegazy.myshop.ui.activity.HomeActivity;
import com.rd.PageIndicatorView;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;
import static com.hegazy.myshop.data.locale.Const.USER_TOKEN;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.LoadData;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;

public class DetailsProductActivity extends AppCompatActivity implements DetailsProductInterface {

    @BindView(R.id.activity_details_product_iv_bake)
    ImageView activityDetailsProductIvBake;
    @BindView(R.id.activity_details_product_iv_fav)
    ImageView activityDetailsProductIvFav;
    @BindView(R.id.activity_details_product_tv_name_product)
    TextView activityDetailsProductTvNameProduct;
    @BindView(R.id.activity_details_product_tv_price)
    TextView activityDetailsProductTvPrice;
    @BindView(R.id.activity_details_product_tv_old_price)
    TextView activityDetailsProductTvOldPrice;
    @BindView(R.id.activity_details_product_tv_discount)
    TextView activityDetailsProductTvDiscount;
    @BindView(R.id.activity_details_product_tv_description)
    TextView activityDetailsProductTvDescription;
    @BindView(R.id.activity_details_product_vp_image)
    ViewPager2 activityDetailsProductVpImage;
    @BindView(R.id.home_pageIndicatorView)
    PageIndicatorView homePageIndicatorView;
    @BindView(R.id.activity_details_product_lin_add_to_cart)
    LinearLayout activityDetailsProductLinAddToCart;

    private ProgressDialog dialog;

    private List<String> imagesList = new ArrayList<>();
    ViewPagerImageProductAdapter viewPagerImageProductAdapter;
    private int current_position;
    private Timer timer;

    DetailsProductPresenter detailsProductPresenter;
    private int idProduct;
    private String token;
    private Integer idProd;
    private boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);
        ButterKnife.bind(this);

        token = LoadData(DetailsProductActivity.this, USER_TOKEN);
        idProduct = getIntent().getExtras().getInt("idProduct");
        detailsProductPresenter = new DetailsProductPresenter(this);

        detailsProductPresenter.DetailsProduct(token, idProduct);

    }


    //Create Slide Show
    public void createSlideShow() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (current_position == imagesList.size())
                    current_position = 0;
                activityDetailsProductVpImage.setCurrentItem(current_position++, true);
                homePageIndicatorView.setCount(imagesList.size());

            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);

            }
        }, 300, 5000);

    }


    @Override
    public void getData(ProductDetailsModelData data) {
        idProd = data.getId();
        activityDetailsProductTvNameProduct.setText(data.getName());
        activityDetailsProductTvPrice.setText(data.getPrice() + " " + "جنية");
        activityDetailsProductTvOldPrice.setText(" السعر بعد الخصم "+" "+data.getOldPrice() + " "+"جنية" );
        activityDetailsProductTvDiscount.setText( " خصم "+" "+data.getDiscount() + " "+"جنية" );
        activityDetailsProductTvDescription.setText(data.getDescription());
        if (data.getInFavorites() == true) {
            activityDetailsProductIvFav.setImageResource(R.drawable.ic_favorite
            );
        }

        if (data.getInCart() == false) {

            activityDetailsProductLinAddToCart.setVisibility(View.VISIBLE);
        }


        imagesList.addAll(data.getImages());
        viewPagerImageProductAdapter = new ViewPagerImageProductAdapter(this, imagesList);
        activityDetailsProductVpImage.setAdapter(viewPagerImageProductAdapter);
        activityDetailsProductVpImage.setClipToPadding(false);
        activityDetailsProductVpImage.setClipChildren(false);
        activityDetailsProductVpImage.setOffscreenPageLimit(1);
        activityDetailsProductVpImage.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();

        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.099f + r + 0.156f);
            }
        });
        activityDetailsProductVpImage.setPageTransformer(compositePageTransformer);


        createSlideShow();

    }


    @Override
    public void showProgress(int title) {
        dialog = showProgressDialog(this, getString(title));
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


    @OnClick({R.id.activity_details_product_iv_bake, R.id.activity_details_product_iv_fav, R.id.activity_details_product_btn_add_to_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_details_product_iv_bake:
                startActivity(new Intent(DetailsProductActivity.this, HomeActivity.class));

                break;
            case R.id.activity_details_product_iv_fav:

                addFavorites(idProd);

                if (check==true)
                {
                    check=false;
                    activityDetailsProductIvFav.setImageResource(R.drawable.ic_favorite);

                }
                else if (check==false)
                {
                    check=true;
                    activityDetailsProductIvFav.setImageResource(R.drawable.ic_favorite_border);

                }


                break;

            case R.id.activity_details_product_btn_add_to_cart:

                addCart(idProd);
                break;

        }
    }


    private void addCart(int id) {
        String token = LoadData(DetailsProductActivity.this, USER_TOKEN);
        dialog = showProgressDialog(DetailsProductActivity.this, getString(R.string.please_wiht));
        dialog.show();

        getClient().addOrRemoveCart(token,changeLang(), id).enqueue(new Callback<AddOrRemoveCartModel>() {
            @Override
            public void onResponse(Call<AddOrRemoveCartModel> call, Response<AddOrRemoveCartModel> response) {
                dialog.dismiss();
                try {

                    if (response.body().getStatus() == true) {

                        MDToast.makeText(DetailsProductActivity.this, response.body().getMessage(), MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
                        activityDetailsProductLinAddToCart.setVisibility(View.GONE);

                    } else if (response.body().getStatus() == false) {

                        MDToast.makeText(DetailsProductActivity.this, response.body().getMessage(), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();

                    }

                } catch (Exception e) {
                    dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<AddOrRemoveCartModel> call, Throwable t) {
                dialog.dismiss();

            }
        });

    }


    private void addFavorites(int id) {
        String token = LoadData(DetailsProductActivity.this, USER_TOKEN);
        dialog = showProgressDialog(DetailsProductActivity.this, getString(R.string.please_wiht));
        dialog.show();

        getClient().addFavorite(token,changeLang(), id).enqueue(new Callback<AddFavoritesModel>() {
            @Override
            public void onResponse(Call<AddFavoritesModel> call, Response<AddFavoritesModel> response) {
                dialog.dismiss();
                try {

                    if (response.body().getStatus() == true) {

                        MDToast.makeText(DetailsProductActivity.this, response.body().getMessage(), MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();


                    } else if (response.body().getStatus() == false) {

                        MDToast.makeText(DetailsProductActivity.this, response.body().getMessage(), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();

                    }

                } catch (Exception e) {
                    dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<AddFavoritesModel> call, Throwable t) {
                dialog.dismiss();

            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(DetailsProductActivity.this, HomeActivity.class));

    }
}