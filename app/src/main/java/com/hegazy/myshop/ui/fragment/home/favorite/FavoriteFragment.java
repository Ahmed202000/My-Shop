package com.hegazy.myshop.ui.fragment.home.favorite;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hegazy.myshop.R;
import com.hegazy.myshop.adapter.FavoritesAdapter;
import com.hegazy.myshop.data.model.favoritesModel.FavoritesModelDatum;
import com.hegazy.myshop.ui.activity.HomeActivity;
import com.hegazy.myshop.ui.fragment.BaseFragment;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hegazy.myshop.data.locale.Const.USER_TOKEN;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.LoadData;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends BaseFragment implements FavoriteInterface{


    @BindView(R.id.fragment_favorite_rv_product)
    RecyclerView fragmentFavoriteRvProduct;

    private List<FavoritesModelDatum> favoritesModelProductList = new ArrayList<>();
    FavoritesAdapter favoritesAdapter;

    private FavoritePresenter favoritePresenter;
    private GridLayoutManager gridLayoutManager;
    private String token;

    private ProgressDialog dialog;
    private HomeActivity activity;


    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

         ButterKnife.bind(this,view);

         favoritePresenter=new FavoritePresenter(this);

         inti();

        return view;


    }

    private void inti() {
        activity=(HomeActivity)getActivity();

        token=LoadData(getActivity(), USER_TOKEN);
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        fragmentFavoriteRvProduct.setLayoutManager(gridLayoutManager);
        favoritePresenter.favorite(token);
    }

    @Override
    public void onback() {

    }

    @Override
    public void getFavorite(List<FavoritesModelDatum> favoritesList) {

        favoritesModelProductList.addAll(favoritesList);
        favoritesAdapter=new FavoritesAdapter(getActivity(),activity,favoritesModelProductList);
        fragmentFavoriteRvProduct.setAdapter(favoritesAdapter);
        favoritesAdapter.notifyDataSetChanged();

    }

    @Override
    public void onSuccess(String massge) {
        // hide dialog
        dialog.cancel();
        MDToast.makeText(getActivity(), massge, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();

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
        dialog = showProgressDialog(getActivity(), getString(title));
        dialog.show();
    }

}
