package com.triton.bertsproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.triton.bertsproject.R;
import com.triton.bertsproject.interfaces.WishlistAddProductListener;
import com.triton.bertsproject.responsepojo.ProductListResponse;
import com.triton.bertsproject.responsepojo.UserAddressListResponse;

import java.util.List;

public class ShippingaddrListAdapter extends RecyclerView.Adapter<ShippingaddrListAdapter.ShoplistHolder> {
    Context context;
    List<UserAddressListResponse.DataBean.AddressBean> addressBeanList;
    View view;
    boolean check;
    WishlistAddProductListener wishlistAddProductListener;

    public ShippingaddrListAdapter(Context context, List<UserAddressListResponse.DataBean.AddressBean> addressBeanList, boolean check, WishlistAddProductListener wishlistAddProductListener) {
        this.context = context;
        this.addressBeanList = addressBeanList ;
        this.check = check;
        this.wishlistAddProductListener=wishlistAddProductListener;

    }

    @NonNull
    @Override
    public ShoplistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_prodlist_gridview, parent, false);

        return new ShoplistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoplistHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return addressBeanList.size();
    }

    public static class ShoplistHolder extends RecyclerView.ViewHolder {
        ImageView img_product_image,img_heart;
        TextView txt_product_name, txt_parts_name,txt_total_reviews,txt_price,txt_stock_status;
  //      RatingBar ratingBar;
        LinearLayout ll_product_status;

        public ShoplistHolder(View itemView) {
            super(itemView);

            img_product_image = itemView.findViewById(R.id.img_product_image);

            txt_product_name = itemView.findViewById(R.id.txt_product_name);

            txt_parts_name = itemView.findViewById(R.id.txt_parts_name);

//            ratingBar = itemView.findViewById(R.id.ratingBar);

            txt_total_reviews = itemView.findViewById(R.id.txt_total_reviews);

            txt_price = itemView.findViewById(R.id.txt_price);

            ll_product_status = itemView.findViewById(R.id.ll_product_status);

            txt_stock_status = itemView.findViewById(R.id.txt_stock_status);

            img_heart = itemView.findViewById(R.id.img_heart);
        }
    }
}