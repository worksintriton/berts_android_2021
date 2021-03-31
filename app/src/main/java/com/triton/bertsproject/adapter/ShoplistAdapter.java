package com.triton.bertsproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.bertsproject.R;
import com.triton.bertsproject.model.ShopByNameModel;
import com.triton.bertsproject.model.ShoplistModel;

import java.util.List;

public class ShoplistAdapter extends RecyclerView.Adapter<ShoplistAdapter.ShoplistHolder> {
    Context context;
    List<ShoplistModel> shoplistModels;
    View view;

    public ShoplistAdapter(Context context, List<ShoplistModel> shoplistModels) {
        this.context = context;
        this.shoplistModels = shoplistModels;

    }

    @NonNull
    @Override
    public ShoplistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_shoplist, parent, false);
        return new ShoplistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoplistHolder holder, final int position) {

        final ShoplistModel shoplistModel = shoplistModels.get(position);

        if (shoplistModel.getShoplistname()!= null) {

                holder.txt_shoplistname.setText(shoplistModel.getShoplistname());

        }

        if (shoplistModel.getImage()!= 0) {

                holder.img_shplst.setImageResource(shoplistModel.getImage());

        }

    }

    @Override
    public int getItemCount() {
        return shoplistModels.size();
    }

    public static class ShoplistHolder extends RecyclerView.ViewHolder {
        ImageView img_shplst;
        TextView txt_shoplistname;
        public ShoplistHolder(View itemView) {
            super(itemView);

            img_shplst = itemView.findViewById(R.id.img_shplst);

            txt_shoplistname = itemView.findViewById(R.id.txt_shoplistname);

        }
    }
}