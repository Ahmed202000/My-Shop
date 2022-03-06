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
import com.hegazy.myshop.data.model.categoriesModel.CategoriesModelData;
import com.hegazy.myshop.data.model.categoriesModel.CategoriesModelDatum;
import com.hegazy.myshop.ui.activity.categoryProducts.CategoryProductsActivity;
import com.hegazy.myshop.ui.activity.productDetails.DetailsProductActivity;
import com.hegazy.myshop.ui.fragment.home.setting.categories.CategoiseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {


    private Context context;
    private CategoiseActivity activity;
    private List<CategoriesModelDatum> categoriesModelDataList = new ArrayList<>();


    public CategoriesAdapter(Context context,CategoiseActivity activity, List<CategoriesModelDatum> categoriesModelDataList) {
        this.context = context;
        this.activity = activity;
        this.categoriesModelDataList = categoriesModelDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_categories,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {


            Glide.with(context).load(categoriesModelDataList.get(position).getImage()).into(holder.itemCategoriesIvLogo);
            holder.itemCategoriesTvName.setText(categoriesModelDataList.get(position).getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, CategoryProductsActivity.class);
                intent.putExtra("idCategory",categoriesModelDataList.get(position).getId());
                context.startActivity(intent);
            }
        });


    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return categoriesModelDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_categories_iv_logo)
        ImageView itemCategoriesIvLogo;
        @BindView(R.id.item_categories_tv_name)
        TextView itemCategoriesTvName;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
