package com.hegazy.myshop.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.notificationsModel.NotificationsModelDatum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {



    private Context context;
    private Activity activity;
    private List<NotificationsModelDatum> notificationsModelDatumList = new ArrayList<>();


    public NotificationAdapter(Context context, List<NotificationsModelDatum> notificationsModelDatumList) {
        this.context = context;
        this.notificationsModelDatumList = notificationsModelDatumList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        holder.itemNotificationTvTitle.setText(notificationsModelDatumList.get(position).getTitle());
        holder.itemNotificationTvMessage.setText(notificationsModelDatumList.get(position).getMessage());

    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return notificationsModelDatumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_notification_tv_title)
        TextView itemNotificationTvTitle;
        @BindView(R.id.item_notification_tv_message)
        TextView itemNotificationTvMessage;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
