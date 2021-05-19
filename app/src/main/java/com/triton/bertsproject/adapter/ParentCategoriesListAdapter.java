package com.triton.bertsproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.triton.bertsproject.R;
import com.triton.bertsproject.responsepojo.FetchAllParentCategoriesResponse;
import com.triton.bertsproject.retailer.ShowAllChildCategActivity;

import java.util.List;

public class ParentCategoriesListAdapter extends RecyclerView.Adapter<ParentCategoriesListAdapter.ViewHolder> {
    Context context;
    List<FetchAllParentCategoriesResponse.DataBean.CategoriesBean> categoriesBeanList;
    View view;
    int size;
    private static final String TAG = "ParentCategoriesListAdapter";

    public ParentCategoriesListAdapter(Context context, List<FetchAllParentCategoriesResponse.DataBean.CategoriesBean> categoriesBeanLists, int size) {
        this.context = context;
        this.categoriesBeanList = categoriesBeanLists;
        this.size=size;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_shoplist, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final FetchAllParentCategoriesResponse.DataBean.CategoriesBean categoriesBean = categoriesBeanList.get(position);

        if (categoriesBean.getName()!= null && !categoriesBean.getName().isEmpty()) {

                holder.txt_shoplistname.setText(categoriesBean.getName());

        }

        if (categoriesBean.getImage_1()!= null && !categoriesBean.getImage_1().isEmpty()) {

            Glide.with(context)
                    .load(categoriesBean.getImage_1())
                    .into(holder.img_shplst);


        }

        holder.cardView.setOnClickListener(v -> {

            Intent intent = new Intent(context, ShowAllChildCategActivity.class);

            intent.putExtra("cate_id",categoriesBean.getId());

            intent.putExtra("cate_name",categoriesBean.getName());

            Log.w(TAG,"parent_id : "+categoriesBean.getId() +"categ_name : "+categoriesBean.getParent_id());

            context.startActivity(intent);

            Animatoo.animateSwipeLeft(context);
        });

    }

    @Override
    public int getItemCount() {
        return size;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_shplst;
        TextView txt_shoplistname;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);

            img_shplst = itemView.findViewById(R.id.img_shplst);

            txt_shoplistname = itemView.findViewById(R.id.txt_shoplistname);

            cardView = itemView.findViewById(R.id.cv_shoplist);

        }
    }
}