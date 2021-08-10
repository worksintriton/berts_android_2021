package com.dci.berts.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.dci.berts.R;
import com.dci.berts.interfaces.GetChildCategIDListener;
import com.dci.berts.responsepojo.FetchChildCateglistResponse;
import com.dci.berts.retailer.RetailerProductListBasedOnCategActivity;
import com.dci.berts.sessionmanager.Connectivity;

import java.util.List;

public class SearchChildCategoriesListAdapter extends RecyclerView.Adapter<SearchChildCategoriesListAdapter.ShopFilterlistHolder> {
    Context context;
    List<FetchChildCateglistResponse.DataBean.CategoriesBean>  categoriesBeanList;
    View view;
    int size;
    String parent_id,categ_name,fromactivity;
    private static final String TAG = "SearchChildCategoriesListAdapter";
    GetChildCategIDListener childCategIDListener;

    public SearchChildCategoriesListAdapter(Context context, List<FetchChildCateglistResponse.DataBean.CategoriesBean> categoriesBeanLists, GetChildCategIDListener childCategIDListener) {
        this.context = context;
        this.categoriesBeanList = categoriesBeanLists;
        this.childCategIDListener = childCategIDListener;

    }

    @NonNull
    @Override
    public ShopFilterlistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filter_yearlist, parent, false);
        return new ShopFilterlistHolder(view);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull ShopFilterlistHolder holder, final int position) {

        final FetchChildCateglistResponse.DataBean.CategoriesBean categoriesBean = categoriesBeanList.get(position);


        if (categoriesBean.getName()!= null&&!categoriesBean.getName().isEmpty()) {

            holder.txt_flistname.setText(categoriesBean.getName());

        }

        holder.cb_flist.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if(isChecked){

                childCategIDListener.getchildcategIDListener(categoriesBean.getId(), categoriesBean.getName());

            }
        });

    }

    @Override
    public int getItemCount() {
        return size;
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