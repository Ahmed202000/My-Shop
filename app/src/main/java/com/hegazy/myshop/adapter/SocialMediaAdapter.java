package com.hegazy.myshop.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.socialMedia.SocialMediaDatum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SocialMediaAdapter extends RecyclerView.Adapter<SocialMediaAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<SocialMediaDatum> socialMediaDatumList = new ArrayList<>();


    public SocialMediaAdapter(Context context, List<SocialMediaDatum> socialMediaDatumList) {
        this.context = context;
        this.socialMediaDatumList = socialMediaDatumList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_social_media,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        Glide.with(context).load(socialMediaDatumList.get(position).getImage()).into(holder.itemSocialMediaIcon);
        holder.itemSocialMediaIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(socialMediaDatumList.get(position).getValue()));
                context.startActivity(browserIntent);
            }
        });

    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return socialMediaDatumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_social_media_icon)
        ImageView itemSocialMediaIcon;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
