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
    String pincode, district, state;

    public ShippingaddrListAdapter(Context context, List<UserAddressListResponse.DataBean.AddressBean> addressBeanList)
    {
        this.context = context;
        this.addressBeanList = addressBeanList ;
    }

    @NonNull
    @Override
    public ShoplistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_shipaddrlist, parent, false);

        return new ShoplistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoplistHolder holder, final int position) {

        final UserAddressListResponse.DataBean.AddressBean addressBean = addressBeanList.get(position);

        if(addressBean.getName()!=null&&!addressBean.getName().isEmpty()){

            holder.txt_username.setText(addressBean.getName());
        }

        if(addressBean.getPhone()!=null&&!addressBean.getPhone().isEmpty()){

            holder.txt_phnum.setText(addressBean.getPhone());
        }

        if(addressBean.getCity()!=null&&!addressBean.getCity().isEmpty()){

            holder.txt_user_city.setText(addressBean.getCity());
        }

        if(addressBean.getAddress1()!=null&&!addressBean.getAddress1().isEmpty()){

            holder.txt_street.setText(addressBean.getAddress1());
        }

        if(addressBean.getState_name()!=null&&!addressBean.getState_name().isEmpty()){

           state = addressBean.getState_name();
        }

        else{

            state = "";

        }

        if(addressBean.getCountry_name()!=null&&!addressBean.getCountry_name().isEmpty()){

            district = addressBean.getCountry_name();
        }

        else{
            district = "";
        }

        if(addressBean.getZipcode()!=null&&!addressBean.getZipcode().isEmpty()){

            pincode = addressBean.getZipcode();
        }

        else{
            pincode = "";
        }

        holder.txt_dist_pincode_state.setText(state+" "+district+" "+pincode);

    }

    @Override
    public int getItemCount() {
        return addressBeanList.size();
    }

    public static class ShoplistHolder extends RecyclerView.ViewHolder {

        TextView txt_username, txt_phnum,txt_user_city,txt_street,txt_dist_pincode_state;
  //      RatingBar ratingBar;
        LinearLayout ll_edit,ll_delete;

        public ShoplistHolder(View itemView) {
            super(itemView);

            txt_username = itemView.findViewById(R.id.txt_username);

            txt_phnum = itemView.findViewById(R.id.txt_phnum);

            txt_user_city = itemView.findViewById(R.id.txt_user_city);

//            ratingBar = itemView.findViewById(R.id.ratingBar);

            txt_street = itemView.findViewById(R.id.txt_street);

            txt_dist_pincode_state = itemView.findViewById(R.id.txt_dist_pincode_state);

            ll_edit = itemView.findViewById(R.id.ll_edit);

            ll_delete = itemView.findViewById(R.id.ll_delete);

        }
    }
}