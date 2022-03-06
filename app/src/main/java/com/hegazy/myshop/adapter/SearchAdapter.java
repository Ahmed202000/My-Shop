package com.hegazy.myshop.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.homeModel.HomeModelProduct;
import com.hegazy.myshop.data.model.searchProductModel.SearchProductModelDatum;
import com.hegazy.myshop.ui.activity.HomeActivity;
import com.hegazy.myshop.ui.activity.productDetails.DetailsProductActivity;
import com.hegazy.myshop.ui.activity.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;
    private SearchActivity activity;
    private List<SearchProductModelDatum> searchProductModelDatumList = new ArrayList<>();


    public SearchAdapter(Context context, SearchActivity activity,List<SearchProductModelDatum> searchProductModelDatumList) {
        this.context = context;
        this.activity = activity;
        this.searchProductModelDatumList = searchProductModelDatumList;
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


            Glide.with(context).load(searchProductModelDatumList.get(position).getImage()).into(holder.itemProductIvLogo);
            holder.itemProductTvName.setText(searchProductModelDatumList.get(position).getName());
            holder.itemProductTvPrice.setText(searchProductModelDatumList.get(position).getPrice() + " "+"جنية");
            holder.itemProductTvDiscount.setText(  searchProductModelDatumList.get(position).getDiscount() + " "+"جنية" +" "+" خصم ");
        if (searchProductModelDatumList.get(position).getDiscount()==null)
        {
            holder.itemProductTvDiscount.setVisibility(View.GONE);
        }

        holder.itemProductTvDescription.setText(searchProductModelDatumList.get(position).getDescription());

            if (searchProductModelDatumList.get(position).getInFavorites()==true)
            {
                holder.itemProductIvFavorite.setImageResource(R.drawable.ic_favorite);
            }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, DetailsProductActivity.class);
                intent.putExtra("idProduct",searchProductModelDatumList.get(position).getId());
                context.startActivity(intent);
            }
        });


    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return searchProductModelDatumList.size();
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


        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
