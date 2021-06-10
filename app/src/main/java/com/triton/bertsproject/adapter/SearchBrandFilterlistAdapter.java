package com.triton.bertsproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triton.bertsproject.R;
import com.triton.bertsproject.interfaces.GetBrandIDListener;
import com.triton.bertsproject.interfaces.GetYearNameListener;
import com.triton.bertsproject.responsepojo.FetchAllBrandsResponse;

import java.util.List;

public class SearchBrandFilterlistAdapter extends RecyclerView.Adapter<SearchBrandFilterlistAdapter.ShopFilterlistHolder> {
    Context context;
    List<FetchAllBrandsResponse.DataBean.BrandBean> BrandBeanList;
    View view;
    GetBrandIDListener getBrandIDListener;
    private static final String TAG = "SearchYearFilterlistAdapter";

    public SearchBrandFilterlistAdapter(Context context, List<FetchAllBrandsResponse.DataBean.BrandBean> BrandBeanList,  GetBrandIDListener getBrandIDListener) {
        this.context = context;
        this.BrandBeanList = BrandBeanList;
        this.getBrandIDListener=getBrandIDListener;

    }

    @NonNull
    @Override
    public ShopFilterlistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filter_yearlist, parent, false);
        return new ShopFilterlistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopFilterlistHolder holder, final int position) {

        final FetchAllBrandsResponse.DataBean.BrandBean BrandBean = BrandBeanList.get(position);

        if (BrandBean.getName()!= null&&!BrandBean.getName().isEmpty()) {

            holder.txt_flistname.setText(BrandBean.getName());

        }

        holder.cb_flist.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if(isChecked){

                getBrandIDListener.getBrandIDListener(BrandBean.getId(), BrandBean.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return BrandBeanList.size();
    }

    public static class ShopFilterlistHolder extends RecyclerView.ViewHolder {

        TextView txt_flistname;

        CheckBox cb_flist;
        public ShopFilterlistHolder(View itemView) {
            super(itemView);
            cb_flist = itemView.findViewById(R.id.cb_flist);
            txt_flistname = itemView.findViewById(R.id.txt_fllistname);

        }
    }
}