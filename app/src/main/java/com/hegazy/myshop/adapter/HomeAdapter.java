package com.hegazy.myshop.adapter;

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
import com.hegazy.myshop.data.model.addFavoritesModel.AddFavoritesModel;
import com.hegazy.myshop.data.model.addOrRemoveCartModel.AddOrRemoveCartModel;
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

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    boolean check;

    private Context context;
    private HomeActivity activity;
    private List<HomeModelProduct> homeModelProductList = new ArrayList<>();
    private ProgressDialog dialog;


    public HomeAdapter(Context context, HomeActivity activity, List<HomeModelProduct> homeModelProductList) {
        this.context = context;
        this.activity = activity;
        this.homeModelProductList = homeModelProductList;
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


        Glide.with(context).load(homeModelProductList.get(position).getImage()).into(holder.itemProductIvLogo);
        holder.itemProductTvName.setText(homeModelProductList.get(position).getName());
        holder.itemProductTvPrice.setText(homeModelProductList.get(position).getPrice() + " " + "جنية");
        holder.itemProductTvDiscount.setText(homeModelProductList.get(position).getDiscount() + " " + "جنية");
        holder.itemProductTvDiscount.setText(homeModelProductList.get(position).getDiscount() + " " + "جنية" + " " + " خصم ");

        holder.itemProductTvDescription.setText(homeModelProductList.get(position).getDescription());



        if (homeModelProductList.get(position).getInFavorites() == true) {
            holder.itemProductIvFavorite.setImageResource(R.drawable.ic_favorite);
        }
         if (homeModelProductList.get(position).getInCart() == true) {
//            holder.itemProductIvCart.setImageResource(R.drawable.ic_cart);
        }


        holder.itemProductIvFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addFavorites(homeModelProductList.get(position).getId());

                if (check == true) {
                    check = false;
                    holder.itemProductIvFavorite.setImageResource(R.drawable.ic_favorite);


                } else if (check == false) {
                    check = true;
                    holder.itemProductIvFavorite.setImageResource(R.drawable.ic_favorite_border);

                }


            }
        });


        holder.itemProductIvCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addCart(homeModelProductList.get(position).getId(),position);

//                if (check == true) {
//                    check = false;
//                    holder.itemProductIvCart.setImageResource(R.drawable.ic_cart);
//
//                } else if (check == false) {
//                    check = true;
//                    holder.itemProductIvCart.setImageResource(R.drawable.ic_shopping_cart);
//
//                }


            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailsProductActivity.class);
                intent.putExtra("idProduct", homeModelProductList.get(position).getId());
                context.startActivity(intent);
            }
        });


    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return homeModelProductList.size();
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

    private void addFavorites(int id) {
        String token = LoadData(activity, USER_TOKEN);
        dialog = showProgressDialog(activity, activity.getString(R.string.please_wiht));
        dialog.show();

        getClient().addFavorite(token,changeLang(), id).enqueue(new Callback<AddFavoritesModel>() {
            @Override
            public void onResponse(Call<AddFavoritesModel> call, Response<AddFavoritesModel> response) {
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
            public void onFailure(Call<AddFavoritesModel> call, Throwable t) {
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
