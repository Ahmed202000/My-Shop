package com.hegazy.myshop.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.getAddressModel.GetAddressModelDatum;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;

public class SpinnerAddressAdapter extends BaseAdapter {

    private Context context;
    private List<GetAddressModelDatum> getAddressModelData = new ArrayList<>();


    public SpinnerAddressAdapter(Context context, List<GetAddressModelDatum> getAddressModelData) {
        this.context = context;
        this.getAddressModelData = getAddressModelData;
    }

    @Override
    public int getCount()
    {
        return getAddressModelData.size();
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item_spinner,
                parent, false);

        TextView itemSpinnerTv=view.findViewById(R.id.item_spinner_tv);



        try{
            if (Locale.getDefault().getDisplayLanguage().equals("English"))
            {

                itemSpinnerTv.setText(getAddressModelData.get(position).getName());
            }
            else
            {
                itemSpinnerTv.setText(getAddressModelData.get(position).getName());

            }

        }
        catch (Exception e)
        {

        }




        return view;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {



        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
