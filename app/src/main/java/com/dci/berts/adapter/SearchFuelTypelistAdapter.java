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
import com.dci.berts.interfaces.GetFuelTypeIDListener;
import com.dci.berts.responsepojo.GetEngineSizeResponse;
import com.dci.berts.responsepojo.GetFuelTypesResponse;

import java.util.List;

public class SearchFuelTypelistAdapter extends RecyclerView.Adapter<SearchFuelTypelistAdapter.ShopFilterlistHolder> {
    Context context;
    List<GetFuelTypesResponse.DataBean.FuelTypesBean> fuelTypesBeanList;
    View view;
    GetFuelTypeIDListener fuelTypeIDListener;
    private static final String TAG = "SearchFuelTypelistAdapter";

    public SearchFuelTypelistAdapter(Context context, List<GetFuelTypesResponse.DataBean.FuelTypesBean> fuelTypesBeanList, GetFuelTypeIDListener fuelTypeIDListener) {
        this.context = context;
        this.fuelTypesBeanList =  fuelTypesBeanList;
        this.fuelTypeIDListener= fuelTypeIDListener;

    }

    @NonNull
    @Override
    public ShopFilterlistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filter_yearlist, parent, false);
        return new ShopFilterlistHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopFilterlistHolder holder, final int position) {

        final GetFuelTypesResponse.DataBean.FuelTypesBean fueltypesbean = fuelTypesBeanList.get(position);

        if (fueltypesbean.getName()!= null&&!fueltypesbean.getName().isEmpty()) {

            holder.txt_flistname.setText(fueltypesbean.getName());

        }

        holder.cb_flist.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if(isChecked){

                fuelTypeIDListener.getfueltypeIDListener(fueltypesbean.getId(), fueltypesbean.getName());

            }
        });
    }

    @Override
    public int getItemCount() {
        return fuelTypesBeanList.size();
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