package com.triton.bertsproject.retailerfragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import com.triton.bertsproject.activities.LoginActivity;
import com.triton.bertsproject.activities.RegisterActivity;
import com.triton.bertsproject.retailer.MyWishlistActivity;
import com.triton.bertsproject.retailer.OrderListActivity;
import com.triton.bertsproject.retailer.RetailerOrderTrackActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_wishlist)
    RelativeLayout rl_wishlist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_ordrhist)
    RelativeLayout rl_ordrhist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_ordrtrack)
    RelativeLayout rl_ordrtrack;

    private static final String TAG = "ProfileFragment";

    View view;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this, view);

        Log.w("Oncreate ", TAG);

        rl_wishlist.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), MyWishlistActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeLeft(Objects.requireNonNull(getContext()));
        });

        rl_ordrhist.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), OrderListActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeRight(Objects.requireNonNull(getContext()));

        });

        rl_ordrtrack.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), RetailerOrderTrackActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeRight(Objects.requireNonNull(getContext()));

        });

        spin_kit_loadingView.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onClick(View v) {

    }
}