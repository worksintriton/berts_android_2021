package com.dci.berts.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dci.berts.R;
import com.dci.berts.api.APIClient;
import com.dci.berts.interfaces.AddProductListener;
import com.dci.berts.interfaces.CartRemoveProductListener;
import com.dci.berts.interfaces.RemoveProductListener;
import com.dci.berts.responsepojo.ShowCartListResponse;
import com.dci.berts.retailer.ProductDetailDescriptionActivity;
import com.dci.berts.sessionmanager.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class CartProductListAdapter extends RecyclerView.Adapter<CartProductListAdapter.ShoplistHolder> {
    Context context;
    List<ShowCartListResponse.DataBean.CartBean> cartBeanList;
    View view;
    CartRemoveProductListener cartRemoveProductListener;
    AddProductListener addProductListener;
    RemoveProductListener removeProductListener;
    Button button;
    String wholesaler_quantity,qty;

    private final static String TAG = "CartProductListAdapter";

    public CartProductListAdapter(Context context2, List<ShowCartListResponse.DataBean.CartBean> cartBeanList,CartRemoveProductListener cartRemoveProductListener,AddProductListener addProductListener,RemoveProductListener removeProductListener) {
        this.context = context2;
        this.cartBeanList = cartBeanList;
        this.cartRemoveProductListener=cartRemoveProductListener;
        this.addProductListener=addProductListener;
        this.removeProductListener=removeProductListener;
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

        if(cartBean.getImages()!=null&&cartBean.getImages().size()>0){

            if (cartBean.getImages().get(0).getImage_default()!= null&&!cartBean.getImages().get(0).getImage_default().isEmpty()) {

                String imgUrl = cartBean.getImages().get(0).getImage_default();

                Glide.with(context)
                        .load(imgUrl)
                        .into(holder.img_product_image);

            }

            else {

                Glide.with(context)
                        .load(APIClient.BASE_IMAGE_URL)
                        .into(holder.img_product_image);
            }
        }

        if (cartBean.getPart_number() != null&&!cartBean.getPart_number().isEmpty()) {

            holder.txt_parts_no.setText(cartBean.getPart_number());

        }
////        if (retailerProductlistModel.getRating() != null) {
////            holder.ratingBar.setNumStars(Integer.parseInt(retailerProductlistModel.getRating()));
////        }
//        if (retailerProductlistModel.getReview() != null) {
//
//            String review = retailerProductlistModel.getReview() + " Reviews";
//
//            holder.txt_total_reviews.setText(review);
//        }


        SessionManager sessionManager = new SessionManager(context);

        HashMap<String, String> user = sessionManager.getProfileDetails();

        String user_role = user.get(SessionManager.KEY_TYPE);

        if (user_role!=null&&user_role.equals("retail")) {

            holder.ll_multipleadd.setVisibility(View.VISIBLE);

            holder.rl_wholesaler_price.setVisibility(View.GONE);


            if (cartBean.getPrice() != null&&!cartBean.getPrice().isEmpty()) {

                String price = "USD " + cartBean.getPrice();

                holder.txt_price.setText(price);
            }

            holder.txt_decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    removeProductListener.removeproductListener(cartBean.getBasket_product_id(),"1",cartBean.getBasket_unit_price());
                }
            });

            holder.txt_increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    addProductListener.addproductListener(cartBean.getBasket_product_id(),"1",cartBean.getBasket_unit_price(),button);

                }
            });

            if (cartBean.getBasket_quantity() != null&&!cartBean.getBasket_quantity().isEmpty()) {

                holder.txt_count_number.setText(cartBean.getBasket_quantity());

            }

        }

        else
        {
            holder.ll_multipleadd.setVisibility(View.GONE);

            holder.rl_wholesaler_price.setVisibility(View.VISIBLE);

            HashMap<String, String> hashMap_wholesaler_price = new HashMap<>();

            ArrayList<String> arrayList = new ArrayList<>();

            for(int i=0;i<cartBeanList.size();i++){

                arrayList.add(cartBeanList.get(i).getWholesaler_price().get(i).getQuantity());

                hashMap_wholesaler_price.put(cartBeanList.get(i).getWholesaler_price().get(i).getQuantity(),cartBeanList.get(i).getWholesaler_price().get(i).getPrice());

            }


            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(context, R.layout.spinner_item, arrayList);

            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);

            holder.spinner.setAdapter(spinnerArrayAdapter);

            if (cartBean.getBasket_quantity() != null&&!cartBean.getBasket_quantity().isEmpty()) {

                qty = cartBean.getBasket_quantity();

                Log.w(TAG,"basket_quantity "+qty);

            }


            int spinnerPosition = spinnerArrayAdapter.getPosition(qty);

            holder.spinner.setSelection(spinnerPosition);

            holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @SuppressLint("LongLogTag")
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ((TextView) parent.getChildAt(0)).setTextColor(context.getResources().getColor(R.color.hint_color));

                    wholesaler_quantity = holder.spinner.getSelectedItem().toString();

                    String prices =  hashMap_wholesaler_price.get(wholesaler_quantity) ;

                    Log.w(TAG,"quantity "+wholesaler_quantity);

                    Log.w(TAG,"price "+prices);

                    if(prices!=null&&!prices.equals("0")){

                        addProductListener.addproductListener(cartBean.getBasket_product_id(),wholesaler_quantity,prices,button);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }
            });

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
        TextView txt_total_reviews,txt_count_number,txt_decrease,txt_increase;
        LinearLayout ll_multipleadd;
        RelativeLayout rl_wholesaler_price;
        Spinner spinner;

        public ShoplistHolder(View itemView) {
            super(itemView);
            img_product_image = itemView.findViewById(R.id.img_product_image);
            img_delete = itemView.findViewById(R.id.img_delete);
            txt_product_name = itemView.findViewById(R.id.txt_product_name);
            txt_parts_no = itemView.findViewById(R.id.txt_parts_name);
            // this.ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            txt_total_reviews =  itemView.findViewById(R.id.txt_total_reviews);
            txt_price = itemView.findViewById(R.id.txt_price);
            txt_count_number = itemView.findViewById(R.id.txt_count_number);
            ll_multipleadd = itemView.findViewById(R.id.ll_multipleadd);
            rl_wholesaler_price = itemView.findViewById(R.id.rl_wholesaler_price);
            txt_increase = itemView.findViewById(R.id.txt_increase);
            txt_decrease = itemView.findViewById(R.id.txt_decrease);
            spinner = itemView.findViewById(R.id.sp_wholesaler_price);
        }
    }
}
