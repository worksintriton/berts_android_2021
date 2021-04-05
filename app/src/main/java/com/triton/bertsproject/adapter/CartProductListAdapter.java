package com.triton.bertsproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.bertsproject.R;
import com.triton.bertsproject.model.RetailerProductlistModel;
import java.util.List;

public class CartProductListAdapter extends RecyclerView.Adapter<CartProductListAdapter.ShoplistHolder> {
    Context context;
    List<RetailerProductlistModel> retailerProductlistModels;
    View view;

    public CartProductListAdapter(Context context2, List<RetailerProductlistModel> retailerProductlistModels2) {
        this.context = context2;
        this.retailerProductlistModels = retailerProductlistModels2;
    }

    @NonNull
    public ShoplistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cart_prodlist, parent, false);
        this.view = inflate;
        return new ShoplistHolder(inflate);
    }

    public void onBindViewHolder(@NonNull ShoplistHolder holder, int position) {
        RetailerProductlistModel retailerProductlistModel = this.retailerProductlistModels.get(position);
        if (retailerProductlistModel.getProduct_name() != null) {
            holder.txt_product_name.setText(retailerProductlistModel.getProduct_name());
        }
        if (retailerProductlistModel.getProdut_image() != 0) {
            holder.img_product_image.setImageResource(retailerProductlistModel.getProdut_image());
        }
        if (retailerProductlistModel.getParts_no() != null) {
            holder.txt_parts_no.setText(retailerProductlistModel.getParts_no());
        }
        if (retailerProductlistModel.getRating() != null) {
            holder.ratingBar.setNumStars(Integer.parseInt(retailerProductlistModel.getRating()));
        }
        if (retailerProductlistModel.getReview() != null) {

            String review = retailerProductlistModel.getReview() + " Reviews";

            holder.txt_total_reviews.setText(review);
        }
        if (retailerProductlistModel.getPrice() != null) {

            String price = "\u0024" + retailerProductlistModel.getPrice();

            holder.txt_price.setText(price);
        }
    }

    public void removeItem(int position) {
        this.retailerProductlistModels.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(RetailerProductlistModel item, int position) {
        this.retailerProductlistModels.add(position, item);
        notifyItemInserted(position);
    }

    public List<RetailerProductlistModel> getData() {
        return this.retailerProductlistModels;
    }

    public int getItemCount() {
        return this.retailerProductlistModels.size();
    }

    public static class ShoplistHolder extends RecyclerView.ViewHolder {
        ImageView img_product_image;
        RatingBar ratingBar;
        TextView txt_parts_no;
        TextView txt_price;
        TextView txt_product_name;
        TextView txt_total_reviews;

        public ShoplistHolder(View itemView) {
            super(itemView);
            this.img_product_image = (ImageView) itemView.findViewById(R.id.img_product_image);
            this.txt_product_name = (TextView) itemView.findViewById(R.id.txt_product_name);
            this.txt_parts_no = (TextView) itemView.findViewById(R.id.txt_parts_name);
            this.ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            this.txt_total_reviews = (TextView) itemView.findViewById(R.id.txt_total_reviews);
            this.txt_price = (TextView) itemView.findViewById(R.id.txt_price);
        }
    }
}
