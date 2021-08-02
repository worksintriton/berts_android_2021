package com.dci.berts.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dci.berts.R;
import com.dci.berts.fragment.RetailerRegisterFragment;
import com.dci.berts.fragment.WholeSalerRegisterFragment;

import org.json.JSONObject;

public class RegisterFragmentPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    String fromActivity;
    JSONObject data,json_data;

    public RegisterFragmentPagerAdapter(Context context, FragmentManager fm, String fromactivity, JSONObject data, JSONObject data_json) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
        this.fromActivity = fromactivity;
        this.data = data;
        this.json_data = data_json;
    }

    // This determines the fragment for each tab
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new RetailerRegisterFragment(fromActivity,data,json_data);
        } else {
            return new WholeSalerRegisterFragment(fromActivity,data,json_data);
        }

    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 2;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.Retailer);
            case 1:
                return mContext.getString(R.string.wholesaler);
            default:
                return null;
        }
    }

}