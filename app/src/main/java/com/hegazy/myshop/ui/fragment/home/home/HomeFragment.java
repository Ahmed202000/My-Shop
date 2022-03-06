package com.hegazy.myshop.ui.fragment.home.home;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.hegazy.myshop.R;
import com.hegazy.myshop.adapter.CategoriesHomeAdapter;
import com.hegazy.myshop.adapter.HomeAdapter;
import com.hegazy.myshop.adapter.ViewPagerBannersAdapter;
import com.hegazy.myshop.data.model.categoriesModel.CategoriesModelDatum;
import com.hegazy.myshop.data.model.homeModel.HomeModelBanner;
import com.hegazy.myshop.data.model.homeModel.HomeModelProduct;
import com.hegazy.myshop.data.model.loginModel.LoginModel;
import com.hegazy.myshop.ui.activity.HomeActivity;
import com.hegazy.myshop.ui.fragment.BaseFragment;
import com.hegazy.myshop.ui.fragment.home.setting.categories.CategoiseActivity;
import com.hegazy.myshop.ui.fragment.home.setting.categories.CategoryInterface;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
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
import static com.hegazy.myshop.data.locale.Const.FIREBASE_TOKEN;
import static com.hegazy.myshop.data.locale.Const.USER_TOKEN;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.LoadData;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements HomeInterface, CategoryInterface {


    @BindView(R.id.fragment_home_vp_ads)
    ViewPager2 fragmentHomeVpAds;
    @BindView(R.id.home_pageIndicatorView)
    DotsIndicator homePageIndicatorView;
    @BindView(R.id.fragment_home_tv_text)
    TextView fragmentHomeTvText;
    @BindView(R.id.fragment_home_rv_product)
    RecyclerView fragmentHomeRvProduct;
    @BindView(R.id.fragment_home_rv_categories)
    RecyclerView fragmentHomeRvCategories;
    @BindView(R.id.fragment_home_tv_all_category)
    TextView fragmentHomeTvAllCategory;


    private List<HomeModelProduct> homeModelProductList = new ArrayList<>();
    HomeAdapter homedapter;
    GridLayoutManager gridLayoutManager;

    private ProgressDialog dialog;


    HomePresenter homePresenter;

    private int current_position;
    private Timer timer;

    private List<HomeModelBanner> homeModelBannerList = new ArrayList<>();
    ViewPagerBannersAdapter viewPagerBannersAdapter;

    private List<CategoriesModelDatum> categoriesModelDataList = new ArrayList<>();
    CategoriesHomeAdapter categoriesHomeAdapter;
    LinearLayoutManager linearLayoutManager;

    private String token;
    private HomeActivity activity;
    private CategoriesHomePresenter categoriesHomePresenter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

        homePresenter = new HomePresenter(this);

        activity = (HomeActivity) getActivity();
        token = LoadData(getActivity(), USER_TOKEN);

        categoriesHomePresenter = new CategoriesHomePresenter(this);
        categoriesHomePresenter.Category();
        homePresenter.home(token);

        setFcm();


        return view;


    }



    private void setFcm()
    {

        String token = LoadData(getActivity(), USER_TOKEN);
        String fcm = LoadData(getActivity(), FIREBASE_TOKEN);

        getClient().fcmToken(token,changeLang(),fcm).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {


                try {

                    if (response.body().getStatus() == true)
                    {
                    }

                    else if (response.body().getStatus() == false)
                    {
                        MDToast.makeText(getActivity(),response.body().getMessage(), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();

                    }
                }
                catch(Exception e)
                {
                }

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

            }
        });

    }






    //Create Slide Show
    public void createSlideShow() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (current_position == homeModelBannerList.size())
                    current_position = 0;
                fragmentHomeVpAds.setCurrentItem(current_position++, true);
                homePageIndicatorView.setViewPager2(fragmentHomeVpAds);


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
    public void getProduct(List<HomeModelProduct> productList) {

        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        fragmentHomeRvProduct.setLayoutManager(gridLayoutManager);

        homeModelProductList.addAll(productList);
        homedapter = new HomeAdapter(getActivity(), activity, homeModelProductList);
        fragmentHomeRvProduct.setAdapter(homedapter);
        homedapter.notifyDataSetChanged();
        dialog.cancel();

    }

    @Override
    public void getBanner(List<HomeModelBanner> bannerList) {

        homeModelBannerList.addAll(bannerList);
        viewPagerBannersAdapter = new ViewPagerBannersAdapter(getActivity(), homeModelBannerList);
        fragmentHomeVpAds.setAdapter(viewPagerBannersAdapter);
        fragmentHomeVpAds.setClipToPadding(false);
        fragmentHomeVpAds.setClipChildren(false);
        fragmentHomeVpAds.setOffscreenPageLimit(1);
        fragmentHomeVpAds.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();

        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.099f + r + 0.156f);
            }
        });
        fragmentHomeVpAds.setPageTransformer(compositePageTransformer);


        createSlideShow();


    }


    @Override
    public void onSuccess(String massge) {
        // hide dialog
        dialog.cancel();
        //MDToast.makeText(getActivity(), massge, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();

    }

    @Override
    public void onError(String mass) {
        // hide dialog
        dialog.cancel();
        // error
        MDToast.makeText(getActivity(), mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();

    }

    @Override
    public void onFailure(String mass) {
        MDToast.makeText(getActivity(), mass, MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();
        dialog.cancel();
    }

    @Override
    public void showProgress(int title) {
        dialog = showProgressDialog(getActivity(), getActivity().getString(title));
        dialog.show();
    }


    @Override
    public void getCategories(List<CategoriesModelDatum> categoryList) {

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        fragmentHomeRvCategories.setLayoutManager(linearLayoutManager);

        categoriesModelDataList.addAll(categoryList);
        categoriesHomeAdapter = new CategoriesHomeAdapter(getActivity(), activity, categoriesModelDataList);
        fragmentHomeRvCategories.setAdapter(categoriesHomeAdapter);
        categoriesHomeAdapter.notifyDataSetChanged();

    }


    @OnClick(R.id.fragment_home_tv_all_category)
    public void onViewClicked() {

        startActivity(new Intent(getActivity(), CategoiseActivity.class));

    }
}
