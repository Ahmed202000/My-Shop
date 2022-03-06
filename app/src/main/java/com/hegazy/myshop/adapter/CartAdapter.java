package com.hegazy.myshop.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.addOrRemoveCartModel.AddOrRemoveCartModel;
import com.hegazy.myshop.data.model.getCartModel.GetCartModelCartItem;
import com.hegazy.myshop.data.model.updateCartMedia.UpdateCartMedia;
import com.hegazy.myshop.ui.activity.OrderActivity;
import com.hegazy.myshop.ui.activity.productDetails.DetailsProductActivity;
import com.hegazy.myshop.ui.fragment.order.cart.CartFragment;
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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {


    private Context context;
    private OrderActivity activity;
    private CartFragment cartFragment;
    private List<GetCartModelCartItem> getCartModelCartItems = new ArrayList<>();
    private ProgressDialog dialog;
    private int count;


    public CartAdapter(Context context,OrderActivity activity, List<GetCartModelCartItem> getCartModelCartItems,CartFragment cartFragment) {
        this.context = context;
        this.activity = activity;
        this.cartFragment = cartFragment;
        this.getCartModelCartItems = getCartModelCartItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        Glide.with(context).load(getCartModelCartItems.get(position).getProduct().getImage()).into(holder.itemCartIvLogo);
        holder.itemCartTvName.setText(getCartModelCartItems.get(position).getProduct().getName());
        holder.itemCartTvPrice.setText(getCartModelCartItems.get(position).getProduct().getOldPrice() + " " + "جنية");
        holder.itemCartTvCount.setText(getCartModelCartItems.get(position).getQuantity() + "");



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailsProductActivity.class);
                intent.putExtra("idProduct", getCartModelCartItems.get(position).getProduct().getId());
                context.startActivity(intent);
            }
        });



        holder.itemCartIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addCartOrRemove(getCartModelCartItems.get(position).getProduct().getId(),position);
            }
        });




        holder.itemCartIvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count = getCartModelCartItems.get(position).getQuantity() + 1;
                getCartModelCartItems.get(position).setQuantity(count);

                holder.itemCartTvCount.setText(count + "");
                updateQunCart(getCartModelCartItems.get(position).getId(),count);
            }
        });




        holder.itemCartIvRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (getCartModelCartItems.get(position).getQuantity()<= 1) {
                    Toast.makeText(context,activity.getString(R.string.This_is_the_lowest_quantity_you_can_buy) , Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    count = getCartModelCartItems.get(position).getQuantity() - 1;
                    getCartModelCartItems.get(position).setQuantity(count);
                    holder.itemCartTvCount.setText(count + "");
                    updateQunCart(getCartModelCartItems.get(position).getId(),count);
                }

            }
        });





    }



    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return getCartModelCartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.item_cart_iv_logo)
        ImageView itemCartIvLogo;
        @BindView(R.id.item_cart_iv_delete)
        ImageView itemCartIvDelete;
        @BindView(R.id.item_cart_tv_name)
        TextView itemCartTvName;
        @BindView(R.id.item_cart_tv_price)
        TextView itemCartTvPrice;
        @BindView(R.id.item_cart_iv_add)
        ImageView itemCartIvAdd;
        @BindView(R.id.item_cart_tv_count)
        TextView itemCartTvCount;
        @BindView(R.id.item_cart_iv_remove)
        ImageView itemCartIvRemove;


        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }


    private void addCartOrRemove(int id, int position) {
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
                        getCartModelCartItems.remove(position);
                        notifyDataSetChanged();
                        cartFragment.inti();



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




    private void updateQunCart(int id,int quantity) {
        String token = LoadData(activity, USER_TOKEN);
//        dialog = showProgressDialog(activity, "إنتظار");
//        dialog.show();

        getClient().updateCart(token,changeLang(), id,quantity).enqueue(new Callback<UpdateCartMedia>() {
            @Override
            public void onResponse(Call<UpdateCartMedia> call, Response<UpdateCartMedia> response) {
            //    dialog.dismiss();
                try {

                    if (response.body().getStatus() == true) {

                        MDToast.makeText(context, response.body().getMessage(), MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
                        cartFragment.inti();

                    } else if (response.body().getStatus() == false) {

                        MDToast.makeText(context, response.body().getMessage(), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();

                    }

                } catch (Exception e) {
                  //  dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<UpdateCartMedia> call, Throwable t) {
            //    dialog.dismiss();

            }
        });

    }


}
