package com.dci.berts.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.dci.berts.R;
import com.dci.berts.api.APIClient;
import com.dci.berts.responsepojo.CouponApplyDetailResponse;
import com.dci.berts.responsepojo.FetchAllBrandsResponse;
import com.dci.berts.retailer.RetailerProductListActivity;
import com.dci.berts.sessionmanager.Connectivity;

import java.util.List;

public class CouponListAdapter extends RecyclerView.Adapter<CouponListAdapter.CouponlistHolder> {
    Context context;
    List<CouponApplyDetailResponse.DataBean> dataBeanList ;
    View view;
    int size;
    private static final String TAG = "CouponListAdapter";
    String fromactivity;
    Connectivity connectivity;

    public CouponListAdapter(Context context, List<CouponApplyDetailResponse.DataBean> dataBeanList, int size, String fromactivity) {
        this.context = context;
        this.dataBeanList = dataBeanList;
        this.size=size;
        this.fromactivity=fromactivity;

    }

    @NonNull
    @Override
    public CouponlistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_coupon_list, parent, false);
        return new CouponlistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CouponlistHolder holder, final int position) {

        final CouponApplyDetailResponse.DataBean dataBean = dataBeanList.get(position);

    /*    if (brandsBean.getName()!= null && !brandsBean.getName().isEmpty()) {

            holder.txt_shoplistname.setText(brandsBean.getName());

        }

        if (brandsBean.getLogo()!= null && !brandsBean.getLogo().isEmpty()&& URLUtil.isValidUrl(brandsBean.getLogo())) {

            Log.w(TAG,"Brand_Img "+brandsBean.getLogo().replaceAll("[\\n\\r\\t]+", ""));

            Glide.with(context)
                    .load(brandsBean.getLogo().replaceAll("[\\n\\r\\t]+", ""))
                    .into(holder.img_shplst);


        }

        else {

            Glide.with(context)
                    .load(APIClient.BASE_IMAGE_URL)
                    .into(holder.img_shplst);
        }*/


        holder.cv_root.setOnClickListener(v -> {


        });

    }

    @Override
    public int getItemCount() {
        return size;
    }

    public static class CouponlistHolder extends RecyclerView.ViewHolder {
        ImageView imvFreeShip;
        TextView tvCoupon,tvDesc,tvDate,tvSaleoff;
        CardView cv_root;
        public CouponlistHolder(View itemView) {
            super(itemView);

            imvFreeShip = itemView.findViewById(R.id.imvFreeShip);

            tvCoupon = itemView.findViewById(R.id.tvCoupon);

            tvDesc = itemView.findViewById(R.id.tvDesc);

            tvDate = itemView.findViewById(R.id.tvDate);

            tvSaleoff = itemView.findViewById(R.id.tvSaleoff);

            cv_root = itemView.findViewById(R.id.cv_root);
        }
    }
}