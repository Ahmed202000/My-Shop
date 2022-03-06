package com.hegazy.myshop.ui.fragment.home.notification;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hegazy.myshop.R;
import com.hegazy.myshop.adapter.NotificationAdapter;
import com.hegazy.myshop.data.model.notificationsModel.NotificationsModelDatum;
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
public class NotificationFragment extends BaseFragment implements NotificationsInterface{


    @BindView(R.id.activity_notification_rv_notification)
    RecyclerView activityNotificationRvNotification;



    private List<NotificationsModelDatum> notificationsModelDatumList = new ArrayList<>();
    NotificationAdapter notificationAdapter;
    LinearLayoutManager linearLayoutManager;


    NotificationsPresenter notificationsPresenter;
    private ProgressDialog dialog;


    String token;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

         ButterKnife.bind(this,view);

        notificationsPresenter=new NotificationsPresenter(this);
        inti();

        return view;


    }

    private void inti() {
        token=LoadData(getActivity(), USER_TOKEN);
        linearLayoutManager=new LinearLayoutManager(getActivity());
        activityNotificationRvNotification.setLayoutManager(linearLayoutManager);
        notificationsPresenter.Notifications(token);
    }

    @Override
    public void onback() {

    }


    @Override
    public void getNotifications(List<NotificationsModelDatum> notificationsList)
    {
        notificationsModelDatumList.addAll(notificationsList);
        notificationAdapter=new NotificationAdapter(getActivity(),notificationsModelDatumList);
        activityNotificationRvNotification.setAdapter(notificationAdapter);
        notificationAdapter.notifyDataSetChanged();
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
