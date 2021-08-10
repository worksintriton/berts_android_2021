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
import com.dci.berts.interfaces.GetFuelTypeIDListener;
import com.dci.berts.interfaces.GetTransmissonIDListener;
import com.dci.berts.responsepojo.GetFuelTypesResponse;
import com.dci.berts.responsepojo.GetTransmissionsResponse;

import java.util.List;

public class SearchTransmisionlistAdapter extends RecyclerView.Adapter<SearchTransmisionlistAdapter.ShopFilterlistHolder> {
    Context context;
    List<GetTransmissionsResponse.DataBean.TransmissionsBean> transmissionsBeanList;
    View view;
    GetTransmissonIDListener transmissonIDListener;
    private static final String TAG = "SearchTransmisionlistAdapter";

    public SearchTransmisionlistAdapter(Context context, List<GetTransmissionsResponse.DataBean.TransmissionsBean> transmissionsBeanList, GetTransmissonIDListener transmissonIDListener) {
        this.context = context;
        this.transmissionsBeanList =  transmissionsBeanList;
        this.transmissonIDListener= transmissonIDListener;

    }

    @NonNull
    @Override
    public ShopFilterlistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filter_yearlist, parent, false);
        return new ShopFilterlistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopFilterlistHolder holder, final int position) {

        final GetTransmissionsResponse.DataBean.TransmissionsBean transmissionbean = transmissionsBeanList.get(position);

        if (transmissionbean.getName()!= null&&!transmissionbean.getName().isEmpty()) {

            holder.txt_flistname.setText(transmissionbean.getName());

        }

        holder.cb_flist.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if(isChecked){

                transmissonIDListener.gettransmissionIDListener(transmissionbean.getId(), transmissionbean.getName());

            }
        });
    }

    @Override
    public int getItemCount() {
        return transmissionsBeanList.size();
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