package com.triton.bertsproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.triton.bertsproject.R;
import com.triton.bertsproject.interfaces.WishlistAddProductListener;
import com.triton.bertsproject.model.RetailerProductlistModel;
import com.triton.bertsproject.responsepojo.ProductListResponse;
import com.triton.bertsproject.retailer.ProductDetailDescriptionActivity;

import java.util.List;

public class RetailerProductListAdapter extends RecyclerView.Adapter<RetailerProductListAdapter.ShoplistHolder> {
    Context context;
    List<ProductListResponse.DataBean.PrdouctsBean> prdouctsBeanList ;
    View view;
    boolean check;
    WishlistAddProductListener wishlistAddProductListener;

    public RetailerProductListAdapter(Context context,List<ProductListResponse.DataBean.PrdouctsBean> prdouctsBeanList , boolean check, WishlistAddProductListener wishlistAddProductListener) {
        this.context = context;
        this.prdouctsBeanList = prdouctsBeanList ;
        this.check = check;
        this.wishlistAddProductListener=wishlistAddProductListener;

    }

    @NonNull
    @Override
    public ShoplistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (check) {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_prodlist_listview, parent, false);

        }

        else {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_prodlist_gridview, parent, false);
        }

        return new ShoplistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoplistHolder holder, final int position) {

        final ProductListResponse.DataBean.PrdouctsBean prdouctsBean = prdouctsBeanList.get(position);

        if (prdouctsBean.getTitle()!= null&&!prdouctsBean.getTitle().isEmpty()) {

                holder.txt_product_name.setText(prdouctsBean.getTitle());

        }

        if (prdouctsBean.getImages().get(0).getImage_default()!= null&&!prdouctsBean.getImages().get(0).getImage_default().isEmpty()) {

                String imgurl = prdouctsBean.getImages().get(0).getImage_default();

                    Glide.with(context)
                    .load(imgurl)
                    .into(holder.img_product_image);

        }

        if (prdouctsBean.getPart_number()!= null&&!prdouctsBean.getPart_number().isEmpty()) {

            holder.txt_parts_name.setText(prdouctsBean.getPart_number());

        }

//        if (retailerProductlistModel.getRating()!= null) {
//
//            holder.ratingBar.setNumStars(Integer.parseInt(retailerProductlistModel.getRating()));
//
//        }

        if (prdouctsBean.getReviews_comments()!= 0) {

            String review = "(" + prdouctsBean.getReviews_comments() + ")";

            holder.txt_total_reviews.setText(review);

        }

        if (prdouctsBean.getPrice()!= null) {

            String price = "$ "+prdouctsBean.getPrice();

            holder.txt_price.setText(price);

        }

        if(prdouctsBean.getQuantity()!=null&&!prdouctsBean.getQuantity().isEmpty()){

            String qty = prdouctsBean.getQuantity();

            if(qty.equals("0")){

                holder.txt_stock_status.setVisibility(View.VISIBLE);

            }

            else {

                holder.txt_stock_status.setVisibility(View.GONE);
            }


        }

        if(prdouctsBean.getTag_new()!=null&&!prdouctsBean.getTag_new().isEmpty()){

            String tag_new = prdouctsBean.getTag_new();

            if(tag_new.equals("1")){

                holder.ll_product_status.setVisibility(View.VISIBLE);

            }

            else {

                holder.ll_product_status.setVisibility(View.GONE);
            }
        }

        if(prdouctsBean.getWishlist()==1){

            holder.img_heart.setImageResource(R.drawable.like);
        }

        else {

            holder.img_heart.setImageResource(R.drawable.ic_heart_outline);
        }

        holder.img_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wishlistAddProductListener.addproductListener(prdouctsBean.getId());

            }
        });

        holder.cv_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context, ProductDetailDescriptionActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return prdouctsBeanList.size();
    }

    public static class ShoplistHolder extends RecyclerView.ViewHolder {
        ImageView img_product_image,img_heart;
        TextView txt_product_name, txt_parts_name,txt_total_reviews,txt_price,txt_stock_status;
  //      RatingBar ratingBar;
        LinearLayout ll_product_status;

        CardView cv_root;

        public ShoplistHolder(View itemView) {
            super(itemView);

            img_product_image = itemView.findViewById(R.id.img_product_image);

            txt_product_name = itemView.findViewById(R.id.txt_product_name);

            txt_parts_name = itemView.findViewById(R.id.txt_parts_name);

            cv_root = itemView.findViewById(R.id.cv_root);

//            ratingBar = itemView.findViewById(R.id.ratingBar);

            txt_total_reviews = itemView.findViewById(R.id.txt_total_reviews);

            txt_price = itemView.findViewById(R.id.txt_price);

            ll_product_status = itemView.findViewById(R.id.ll_product_status);

            txt_stock_status = itemView.findViewById(R.id.txt_stock_status);

            img_heart = itemView.findViewById(R.id.img_heart);
        }
    }
}