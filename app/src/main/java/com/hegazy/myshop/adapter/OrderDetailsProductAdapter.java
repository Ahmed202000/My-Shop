package com.hegazy.myshop.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.orderDetailsModel.OrderDetailsModelProduct;
import com.hegazy.myshop.ui.activity.productDetails.DetailsProductActivity;
import com.hegazy.myshop.ui.activity.setting.orderDetails.OrderDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailsProductAdapter extends RecyclerView.Adapter<OrderDetailsProductAdapter.ViewHolder> {


    private Context context;
    private OrderDetailsActivity activity;
    private List<OrderDetailsModelProduct> orderDetailsModelProductList = new ArrayList<>();


    public OrderDetailsProductAdapter(Context context,OrderDetailsActivity activity, List<OrderDetailsModelProduct> orderDetailsModelProductList) {
        this.context = context;
        this.activity = activity;
        this.orderDetailsModelProductList = orderDetailsModelProductList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_order,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        Glide.with(context)
                .load(orderDetailsModelProductList.get(position).getImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);

                        return false;
                    }


                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.itemProductOrderIvLogo);

        holder.itemProductOrderTvName.setText(orderDetailsModelProductList.get(position).getName());
        holder.itemProductOrderTvPrice.setText(orderDetailsModelProductList.get(position).getPrice() + "");
        holder.itemProductOrderTvQuantity.setText(orderDetailsModelProductList.get(position).getQuantity() + "");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailsProductActivity.class);
                intent.putExtra("idProduct", orderDetailsModelProductList.get(position).getId());
                context.startActivity(intent);
            }
        });

    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return orderDetailsModelProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_product_order_iv_logo)
        ImageView itemProductOrderIvLogo;
        @BindView(R.id.item_product_order_tv_name)
        TextView itemProductOrderTvName;
        @BindView(R.id.item_product_order_tv_price)
        TextView itemProductOrderTvPrice;
        @BindView(R.id.item_product_order_tv_quantity)
        TextView itemProductOrderTvQuantity;
        @BindView(R.id.progress_bar)
        ProgressBar progressBar;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
