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
import com.triton.bertsproject.responsepojo.SearchProductsResponse;
import com.triton.bertsproject.retailer.ProductDetailDescriptionActivity;

import java.util.List;

public class SearchProductListAdapter extends RecyclerView.Adapter<SearchProductListAdapter.ShoplistHolder> {
    Context context;
    List<SearchProductsResponse.DataBean.PrdouctsBean> prdouctsBeanList ;
    View view;

    public SearchProductListAdapter(Context context,List<SearchProductsResponse.DataBean.PrdouctsBean> prdouctsBeanList) {
        this.context = context;
        this.prdouctsBeanList = prdouctsBeanList ;

    }

    @NonNull
    @Override
    public ShoplistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_searchprodlist, parent, false);

        return new ShoplistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoplistHolder holder, final int position) {

        final SearchProductsResponse.DataBean.PrdouctsBean prdouctsBean = prdouctsBeanList.get(position);

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

        }
    }
}