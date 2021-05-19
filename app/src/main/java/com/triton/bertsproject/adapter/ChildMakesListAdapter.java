package com.triton.bertsproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.triton.bertsproject.R;
import com.triton.bertsproject.responsepojo.FetchAllParentMakesResponse;
import com.triton.bertsproject.responsepojo.FetchChildMakeslistRequestResponse;

import java.util.List;

public class ChildMakesListAdapter extends RecyclerView.Adapter<ChildMakesListAdapter.ShoplistHolder> {
    Context context;
    List<FetchChildMakeslistRequestResponse.DataBean.MakeBean> makesBeanList;
    View view;
    int size;

    public ChildMakesListAdapter(Context context, List<FetchChildMakeslistRequestResponse.DataBean.MakeBean> makesBeanLists, int size) {
        this.context = context;
        this.makesBeanList = makesBeanLists;
        this.size = size;

    }

    @NonNull
    @Override
    public ShoplistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_modellist, parent, false);
        return new ShoplistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoplistHolder holder, final int position) {

        final FetchChildMakeslistRequestResponse.DataBean.MakeBean makesBean = makesBeanList.get(position);

        if (makesBean.getName()!= null && !makesBean.getName().isEmpty()) {

            holder.txt_shoplistname.setText(makesBean.getName());

        }

//        if (makesBean.getLogo()!= null && !makesBean.getLogo().isEmpty()) {
//
//            Glide.with(context)
//                    .load(makesBean.getLogo())
//                    .into(holder.img_shplst);
//
//
//        }

//        holder.cardView.setOnClickListener(v -> {
//
//            context.startActivity(new Intent(context, RetailerProductListActivity.class));
//
//            Animatoo.animateSwipeLeft(context);
//        });

    }

    @Override
    public int getItemCount() {
        return size;
    }

    public static class ShoplistHolder extends RecyclerView.ViewHolder {
        ImageView img_shplst;
        TextView txt_shoplistname;
        LinearLayout ll_shoplist;
        public ShoplistHolder(View itemView) {
            super(itemView);

            img_shplst = itemView.findViewById(R.id.img_shplst);

            txt_shoplistname = itemView.findViewById(R.id.txt_shoplistname);

            ll_shoplist = itemView.findViewById(R.id.ll_shoplist);

        }
    }
}