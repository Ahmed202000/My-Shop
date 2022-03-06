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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.addOrRemoveCartModel.AddOrRemoveCartModel;
import com.hegazy.myshop.data.model.getAddressModel.GetAddressModel;
import com.hegazy.myshop.data.model.getAddressModel.GetAddressModelDatum;
import com.hegazy.myshop.ui.activity.setting.address.allAdderss.AllAddressActivity;
import com.hegazy.myshop.ui.activity.setting.address.updateAddress.UpdateAddressActivity;
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

public class AllAddressAdapter extends RecyclerView.Adapter<AllAddressAdapter.ViewHolder> {

    private Context context;
    private AllAddressActivity activity;
    private List<GetAddressModelDatum> getAddressModelDatumList = new ArrayList<>();
    private ProgressDialog dialog;


    public AllAddressAdapter(Context context,AllAddressActivity activity, List<GetAddressModelDatum> getAddressModelDatumList) {
        this.context = context;
        this.activity = activity;
        this.getAddressModelDatumList = getAddressModelDatumList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_address,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        holder.itemAddressTvTitle.setText(getAddressModelDatumList.get(position).getName());

        holder.itemAddressIvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, UpdateAddressActivity.class);
                intent.putExtra("id",getAddressModelDatumList.get(position).getId());
                intent.putExtra("name",getAddressModelDatumList.get(position).getName());
                intent.putExtra("city",getAddressModelDatumList.get(position).getCity());
                intent.putExtra("region",getAddressModelDatumList.get(position).getRegion());
                intent.putExtra("details",getAddressModelDatumList.get(position).getDetails());
                intent.putExtra("notes",getAddressModelDatumList.get(position).getNotes());
                context.startActivity(intent);

            }
        });


        holder.itemAddressIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteAddress(getAddressModelDatumList.get(position).getId(),position);

            }
        });


    }



    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return getAddressModelDatumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_address_tv_title)
        TextView itemAddressTvTitle;
        @BindView(R.id.item_address_iv_edit)
        ImageView itemAddressIvEdit;
        @BindView(R.id.item_address_iv_delete)
        ImageView itemAddressIvDelete;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }

    private void deleteAddress(Integer id, int position) {

        String token = LoadData(activity, USER_TOKEN);
        dialog = showProgressDialog(activity, activity.getString(R.string.please_wiht));
        dialog.show();

        getClient().deleteAddress(token,changeLang(), id).enqueue(new Callback<GetAddressModel>() {
            @Override
            public void onResponse(Call<GetAddressModel> call, Response<GetAddressModel> response) {
                dialog.dismiss();
                try {

                    if (response.body().getStatus() == true) {

                        MDToast.makeText(context, response.body().getMessage(), MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show();
                        getAddressModelDatumList.remove(position);
                        notifyDataSetChanged();


                    } else if (response.body().getStatus() == false) {

                        MDToast.makeText(context, response.body().getMessage(), MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show();

                    }

                } catch (Exception e) {
                    dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<GetAddressModel> call, Throwable t) {
                dialog.dismiss();

            }
        });

    }

}
