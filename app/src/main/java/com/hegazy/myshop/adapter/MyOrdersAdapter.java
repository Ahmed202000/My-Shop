package com.hegazy.myshop.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.addOrRemoveCartModel.AddOrRemoveCartModel;
import com.hegazy.myshop.data.model.myOrderModel.MyOrderModelDatum;
import com.hegazy.myshop.data.model.promoCodeModel.PromoCodeModel;
import com.hegazy.myshop.ui.activity.AuthActivity;
import com.hegazy.myshop.ui.activity.setting.myOrders.MyOrdersActivity;
import com.hegazy.myshop.ui.activity.setting.orderDetails.OrderDetailsActivity;
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

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder> {

    private Context context;
    private MyOrdersActivity activity;
    private List<MyOrderModelDatum> myOrderModelDatumList = new ArrayList<>();
    private ProgressDialog dialog;
    private Dialog dialog1;

    public MyOrdersAdapter(Context context, MyOrdersActivity activity, List<MyOrderModelDatum> myOrderModelDatumList) {
        this.context = context;
        this.activity = activity;
        this.myOrderModelDatumList = myOrderModelDatumList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_orders,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        holder.itemMyOrdersTvTotal.setText(myOrderModelDatumList.get(position).getTotal()+"");
        holder.itemMyOrdersTvOrderDate.setText(myOrderModelDatumList.get(position).getDate());
        holder.itemMyOrdersTvStatus.setText(myOrderModelDatumList.get(position).getStatus());

        if (myOrderModelDatumList.get(position).getStatus().equals("New") || myOrderModelDatumList.get(position).getStatus().equals("جديد"))
        {
            holder.itemMyOrdersTvStatus.setTextColor(context.getResources().getColor(R.color.backrund));
            holder.itemMyOrdersBtnCancel.setVisibility(View.VISIBLE);
        }
        else  if (myOrderModelDatumList.get(position).getStatus().equals("Cancelled") || myOrderModelDatumList.get(position).getStatus().equals("ملغي"))
        {
            holder.itemMyOrdersTvStatus.setTextColor(context.getResources().getColor(R.color.Cancelled));
        }
        else
        {
            holder.itemMyOrdersTvStatus.setTextColor(context.getResources().getColor(R.color.colorSuccess));
        }

        holder.itemMyOrdersBtnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("idOrder",myOrderModelDatumList.get(position).getId());
                context.startActivity(intent);
            }
        });

        holder.itemMyOrdersBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogCancelOrder(myOrderModelDatumList.get(position).getId(),position);
            }
        });
    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return myOrderModelDatumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_my_orders_tv_total)
        TextView itemMyOrdersTvTotal;
        @BindView(R.id.item_my_orders_tv_order_date)
        TextView itemMyOrdersTvOrderDate;
        @BindView(R.id.item_my_orders_tv_status)
        TextView itemMyOrdersTvStatus;
        @BindView(R.id.item_my_orders_btn_details)
        Button itemMyOrdersBtnDetails;
        @BindView(R.id.item_my_orders_btn_cancel)
        Button itemMyOrdersBtnCancel;


        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }




    //TODO dialog cancel Order
    private void dialogCancelOrder(int id,int position) {
        dialog1 = new Dialog(context, R.style.customDialogTheme);
        dialog1.setCancelable(false);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dialog_cancel_order, null);
        dialog1.setContentView(v);


        TextView yes = dialog1.findViewById(R.id.dialog_cancel_order_tv_yes);
        TextView no = dialog1.findViewById(R.id.dialog_cancel_order_tv_no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelOrder(id ,position);
            }
        });
        dialog1.show();
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
        dialog1.show();
    }

    private void cancelOrder(int id, int position) {

        String token = LoadData(activity, USER_TOKEN);
        dialog = showProgressDialog(activity, "إنتظار");
        dialog.show();

        getClient().cancelOrder(token,changeLang(),id).enqueue(new Callback<PromoCodeModel>() {
            @Override
            public void onResponse(Call<PromoCodeModel> call, Response<PromoCodeModel> response) {
                dialog.dismiss();
                try {

                    if (response.body().getStatus() == true) {

                        MDToast.makeText(context, response.body().getMessage(), MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
                       dialog1.dismiss();
                        activity.inti();


                    } else if (response.body().getStatus() == false) {

                        MDToast.makeText(context, response.body().getMessage(), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();

                    }

                } catch (Exception e) {
                    dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<PromoCodeModel> call, Throwable t) {
                dialog.dismiss();

            }
        });



    }
}
