package com.hegazy.myshop.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.addOrRemoveCartModel.AddOrRemoveCartModel;
import com.hegazy.myshop.data.model.deleteFavoritesModel.DeleteFavoritesModel;
import com.hegazy.myshop.data.model.favoritesModel.FavoritesModelData;
import com.hegazy.myshop.data.model.favoritesModel.FavoritesModelDatum;
import com.hegazy.myshop.data.model.favoritesModel.FavoritesModelProduct;
import com.hegazy.myshop.data.model.homeModel.HomeModelProduct;
import com.hegazy.myshop.ui.activity.HomeActivity;
import com.hegazy.myshop.ui.activity.productDetails.DetailsProductActivity;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hegazy.myshop.data.api.RetrofitClient.getClient;
import static com.hegazy.myshop.data.locale.Const.USER_TOKEN;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.LoadData;
import static com.hegazy.myshop.helpar.HelperMethod.changeLang;
import static com.hegazy.myshop.helpar.HelperMethod.showProgressDialog;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private Context context;
    private HomeActivity activity;
    private List<FavoritesModelDatum> favoritesModelProductList = new ArrayList<>();
    private ProgressDialog dialog;


    public FavoritesAdapter(Context context,HomeActivity activity, List<FavoritesModelDatum> favoritesModelProductList) {
        this.context = context;
        this.activity = activity;
        this.favoritesModelProductList = favoritesModelProductList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, DetailsProductActivity.class);
                intent.putExtra("idProduct",favoritesModelProductList.get(position).getProduct().getId());
                context.startActivity(intent);
            }
        });



        Glide.with(context).load(favoritesModelProductList.get(position).getProduct().getImage()).into(holder.itemProductIvLogo);
            holder.itemProductTvName.setText(favoritesModelProductList.get(position).getProduct().getName());
            holder.itemProductTvPrice.setText(favoritesModelProductList.get(position).getProduct().getOldPrice() + " "+"جنية");
            holder.itemProductTvDiscount.setText(  favoritesModelProductList.get(position).getProduct().getDiscount() + " "+"جنية" +" "+" خصم ");
            holder.itemProductTvDescription.setText(favoritesModelProductList.get(position).getProduct().getDescription());

            if (favoritesModelProductList.get(position).getProduct().getDiscount().equals("0"))
            {
                holder.itemProductTvDiscount.setVisibility(View.GONE);
            }


            holder.itemProductIvFavorite.setImageResource(R.drawable.ic_favorite);

            holder.itemProductIvFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteFavorites(favoritesModelProductList.get(position).getId(),position);
                }
            });


        holder.itemProductIvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addCart(favoritesModelProductList.get(position).getProduct().getId(),position);


            }
        });

    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return favoritesModelProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_product_iv_favorite)
        ImageView itemProductIvFavorite;
        @BindView(R.id.item_product_iv_logo)
        ImageView itemProductIvLogo;
        @BindView(R.id.item_product_tv_name)
        TextView itemProductTvName;
        @BindView(R.id.item_product_tv_price)
        TextView itemProductTvPrice;
        @BindView(R.id.item_product_tv_discount)
        TextView itemProductTvDiscount;
        @BindView(R.id.item_product_tv_description)
        TextView itemProductTvDescription;
        @BindView(R.id.item_product_iv_cart)
        LinearLayout itemProductIvCart;



        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }

    private void deleteFavorites(int id,int position)
    {
        String token = LoadData(activity, USER_TOKEN);
        dialog=showProgressDialog(activity,activity.getString(R.string.please_wiht));
        dialog.show();

        getClient().deleteFavorite(token,changeLang(),id).enqueue(new Callback<DeleteFavoritesModel>() {
            @Override
            public void onResponse(Call<DeleteFavoritesModel> call, Response<DeleteFavoritesModel> response)
            {
                dialog.dismiss();
                try {

                    if (response.body().getStatus()==true) {

                        MDToast.makeText(context, response.body().getMessage(), MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
                        favoritesModelProductList.remove(position);
                        notifyDataSetChanged();

                    }
                    else if (response.body().getStatus()==false) {

                        MDToast.makeText(context, response.body().getMessage(), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();

                    }

                }
                catch (Exception e)
                {
                    dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<DeleteFavoritesModel> call, Throwable t) {
                dialog.dismiss();

            }
        });

    }





    private void addCart(int id,int position) {
        String token = LoadData(activity, USER_TOKEN);
        dialog = showProgressDialog(activity, activity.getString(R.string.please_wiht));
        dialog.show();

        getClient().addOrRemoveCart(token,changeLang(), id).enqueue(new Callback<AddOrRemoveCartModel>() {
            @Override
            public void onResponse(Call<AddOrRemoveCartModel> call, Response<AddOrRemoveCartModel> response) {
                dialog.dismiss();
                try {

                    if (response.body().getStatus() == true) {

                        MDToast.makeText(context, response.body().getMessage(), MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();

                    } else if (response.body().getStatus() == false) {

                        MDToast.makeText(context, response.body().getMessage(), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();

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

}
