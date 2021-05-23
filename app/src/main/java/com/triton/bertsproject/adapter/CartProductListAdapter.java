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
import com.triton.bertsproject.interfaces.CartRemoveProductListener;
import com.triton.bertsproject.model.RetailerProductlistModel;
import com.triton.bertsproject.responsepojo.ShowCartListResponse;

import java.util.List;

public class CartProductListAdapter extends RecyclerView.Adapter<CartProductListAdapter.ShoplistHolder> {
    Context context;
    List<ShowCartListResponse.DataBean.CartBean> cartBeanList;
    View view;
    CartRemoveProductListener cartRemoveProductListener;

    public CartProductListAdapter(Context context2, List<ShowCartListResponse.DataBean.CartBean> cartBeanList,CartRemoveProductListener cartRemoveProductListener) {
        this.context = context2;
        this.cartBeanList = cartBeanList;
        this.cartRemoveProductListener=cartRemoveProductListener;
    }

    @NonNull
    public ShoplistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cart_prodlist, parent, false);
        this.view = inflate;
        return new ShoplistHolder(inflate);
    }

    public void onBindViewHolder(@NonNull ShoplistHolder holder, int position) {
        ShowCartListResponse.DataBean.CartBean cartBean = this.cartBeanList.get(position);


        if (cartBean.getTitle() != null&&!cartBean.getTitle().isEmpty())  {
            holder.txt_product_name.setText(cartBean.getTitle());
        }
        if (cartBean.getImages().get(0).getImage_default()!= null&&!cartBean.getImages().get(0).getImage_default().isEmpty()) {

            String imgUrl = cartBean.getImages().get(0).getImage_default();

            Glide.with(context)
                    .load(imgUrl)
                    .into(holder.img_product_image);

        }
//        if (retailerProductlistModel.getParts_no() != null) {
//            holder.txt_parts_no.setText(retailerProductlistModel.getParts_no());
//        }
////        if (retailerProductlistModel.getRating() != null) {
////            holder.ratingBar.setNumStars(Integer.parseInt(retailerProductlistModel.getRating()));
////        }
//        if (retailerProductlistModel.getReview() != null) {
//
//            String review = retailerProductlistModel.getReview() + " Reviews";
//
//            holder.txt_total_reviews.setText(review);
//        }
        if (cartBean.getPrice() != null) {

            String price = "\u0024" + cartBean.getPrice();

            holder.txt_price.setText(price);
        }

        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cartRemoveProductListener.removeproductListener(cartBean.getBasket_id());
            }
        });
    }


    public int getItemCount() {
        return cartBeanList.size();
    }

    public static class ShoplistHolder extends RecyclerView.ViewHolder {
        ImageView img_product_image,img_delete;
     //   RatingBar ratingBar;
        TextView txt_parts_no;
        TextView txt_price;
        TextView txt_product_name;
        TextView txt_total_reviews;
        LinearLayout ll_multipleadd;

        public ShoplistHolder(View itemView) {
            super(itemView);
            img_product_image = itemView.findViewById(R.id.img_product_image);
            img_delete = itemView.findViewById(R.id.img_delete);
            txt_product_name = itemView.findViewById(R.id.txt_product_name);
            txt_parts_no = itemView.findViewById(R.id.txt_parts_name);
           // this.ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            txt_total_reviews =  itemView.findViewById(R.id.txt_total_reviews);
            txt_price = itemView.findViewById(R.id.txt_price);
            ll_multipleadd = itemView.findViewById(R.id.ll_multipleadd);
        }
    }
}
