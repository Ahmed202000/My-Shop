package com.hegazy.myshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.homeModel.HomeModelBanner;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ViewPagerBannersAdapter extends RecyclerView.Adapter<ViewPagerBannersAdapter.ViewHolder> {


    private Context context;
    private List<HomeModelBanner> homeModelBannerList = new ArrayList<>();

    public ViewPagerBannersAdapter(Context context, List<HomeModelBanner> homeModelBannerList) {
        this.context = context;
        this.homeModelBannerList = homeModelBannerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_pager_banners_home,
                parent, false);


        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        Glide.with(context).load(homeModelBannerList.get(position).getImage()).into(holder.itemItemViewPagerIvBannersHome);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return homeModelBannerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_item_view_pager_iv_banners_home)
        RoundedImageView itemItemViewPagerIvBannersHome;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
