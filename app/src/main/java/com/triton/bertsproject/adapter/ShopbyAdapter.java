package com.triton.bertsproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.bertsproject.R;
import com.triton.bertsproject.model.ShopByNameModel;

import java.util.List;

public class ShopbyAdapter extends RecyclerView.Adapter<ShopbyAdapter.ShopbyHolder> {
    Context context;
    List<ShopByNameModel> shopByNameModels;
    View view;

    public ShopbyAdapter(Context context, List<ShopByNameModel> shopByNameModels) {
        this.context = context;
        this.shopByNameModels = shopByNameModels;

    }

    @NonNull
    @Override
    public ShopbyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_shopbyname, parent, false);
        return new ShopbyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopbyHolder holder, final int position) {

        final ShopByNameModel shopByNameModel = shopByNameModels.get(position);

        if (shopByNameModel.getShopbyname()!= null) {

                holder.txt_shopbyname.setText(shopByNameModel.getShopbyname());

        }




    }

    @Override
    public int getItemCount() {
        return shopByNameModels.size();
    }

    public static class ShopbyHolder extends RecyclerView.ViewHolder {
        TextView txt_shopbyname;
        public ShopbyHolder(View itemView) {
            super(itemView);
            txt_shopbyname = itemView.findViewById(R.id.txt_shopbyname);

        }
    }
}