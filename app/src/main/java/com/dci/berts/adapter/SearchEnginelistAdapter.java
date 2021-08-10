package com.dci.berts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dci.berts.R;
import com.dci.berts.interfaces.GetEngineIDListener;
import com.dci.berts.responsepojo.GetEngineSizeResponse;

import java.util.List;

public class SearchEnginelistAdapter extends RecyclerView.Adapter<SearchEnginelistAdapter.ShopFilterlistHolder> {
    Context context;
    List<GetEngineSizeResponse.DataBean.EngineSizeBean> enginebeanlist;
    View view;
    GetEngineIDListener engineIDListener;
    private static final String TAG = "SearchEnginelistAdapter";

    public SearchEnginelistAdapter(Context context, List<GetEngineSizeResponse.DataBean.EngineSizeBean> enginebeanlist, GetEngineIDListener engineIDListener) {
        this.context = context;
        this.enginebeanlist =  enginebeanlist;
        this.engineIDListener= engineIDListener;

    }

    @NonNull
    @Override
    public ShopFilterlistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filter_yearlist, parent, false);
        return new ShopFilterlistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopFilterlistHolder holder, final int position) {

        final GetEngineSizeResponse.DataBean.EngineSizeBean engineSizeBean = enginebeanlist.get(position);

        if (engineSizeBean.getName()!= null&&!engineSizeBean.getName().isEmpty()) {

            holder.txt_flistname.setText(engineSizeBean.getName());

        }

        holder.cb_flist.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if(isChecked){

                engineIDListener.getengineIDListener(engineSizeBean.getId(), engineSizeBean.getName());

            }
        });
    }

    @Override
    public int getItemCount() {
        return enginebeanlist.size();
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