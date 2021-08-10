package com.dci.berts.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dci.berts.R;
import com.dci.berts.interfaces.GetThirdCategIDListener;
import com.dci.berts.responsepojo.GetThirdCategoryResponse;

import java.util.List;

public class SearchThirdChildCategoriesListAdapter extends RecyclerView.Adapter<SearchThirdChildCategoriesListAdapter.ShopFilterlistHolder> {
    Context context;
    List<GetThirdCategoryResponse.DataBean.CategoriesBean>  categoriesBeanList;
    View view;
    int size;
    String parent_id,categ_name,fromactivity;
    private static final String TAG = "SearchThirdChildCategoriesListAdapter";
    GetThirdCategIDListener thirdCategIDListener;

    public SearchThirdChildCategoriesListAdapter(Context context, List<GetThirdCategoryResponse.DataBean.CategoriesBean> categoriesBeanLists, GetThirdCategIDListener thirdCategIDListener) {
        this.context = context;
        this.categoriesBeanList = categoriesBeanLists;
        this.thirdCategIDListener = thirdCategIDListener;

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

        final GetThirdCategoryResponse.DataBean.CategoriesBean categoriesBean = categoriesBeanList.get(position);


        if (categoriesBean.getName()!= null&&!categoriesBean.getName().isEmpty()) {

            holder.txt_flistname.setText(categoriesBean.getName());

        }

        holder.cb_flist.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if(isChecked){

                thirdCategIDListener.getthirdcategIDListener(categoriesBean.getId(), categoriesBean.getName());

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