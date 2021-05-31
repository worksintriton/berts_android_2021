package com.triton.bertsproject.retailerfragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.triton.bertsproject.R;
import com.triton.bertsproject.activities.LoginActivity;
import com.triton.bertsproject.activities.RegisterActivity;
import com.triton.bertsproject.adapter.ShopbyAdapter;
import com.triton.bertsproject.model.ShopByNameModel;
import com.triton.bertsproject.retailer.AddVehicleActivity;
import com.triton.bertsproject.retailer.RetailerCartActivity;
import com.triton.bertsproject.retailer.SearchProductsActivity;
import com.triton.bertsproject.sessionmanager.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements View.OnClickListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_signin)
    Button btn_sigin;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_user_login)
    TextView txt_user_login;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_search)
    EditText edt_search;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fl_cart)
    FrameLayout fl_cart;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_shopby)
    RecyclerView rv_shopby;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_addVeh)
    Button btn_addVeh;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_user_type)
    TextView txt_user_type;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cl_loginbefore)
    CardView cl_loginbefore;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_vehicle)
    CardView cv_vehicle;

    List<ShopByNameModel> shopByNameModels;

    private static final String TAG = "HomeFragment";

    View view;

    String username;

    SessionManager sessionManager;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

        Log.w("Oncreate ", TAG);

        sessionManager = new SessionManager(getContext());

        HashMap<String, String> user = sessionManager.getProfileDetails();

        if(sessionManager.isLoggedIn())
        {
            cl_loginbefore.setVisibility(View.GONE);

            username = user.get(SessionManager.KEY_FIRST_NAME) + " " + user.get(SessionManager.KEY_LAST_NAME);

            txt_user_type.setText(""+username);

            cv_vehicle.setVisibility(View.VISIBLE);

        }

        else {

            cl_loginbefore.setVisibility(View.VISIBLE);

            cv_vehicle.setVisibility(View.GONE);

            txt_user_login.setOnClickListener(v -> {

                Intent intent = new Intent(getContext(), LoginActivity.class);

                intent.putExtra("fromActivity",TAG);

                startActivity(intent);

                Animatoo.animateSwipeLeft(Objects.requireNonNull(getContext()));
            });

            btn_sigin.setOnClickListener(v -> {

                Intent intent = new Intent(getContext(), RegisterActivity.class);

                intent.putExtra("fromActivity",TAG);

                startActivity(intent);

                Animatoo.animateSwipeRight(Objects.requireNonNull(getContext()));

            });

        }

        edt_search.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), SearchProductsActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeLeft(Objects.requireNonNull(getContext()));

        });

        btn_addVeh.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), AddVehicleActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeLeft(Objects.requireNonNull(getContext()));
        });

        fl_cart.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), RetailerCartActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeLeft(Objects.requireNonNull(getContext()));
        });


        spin_kit_loadingView.setVisibility(View.GONE);

        setView();

        return view;
    }

    private void setView() {

        shopByNameModels = new ArrayList<>();

        shopByNameModels.add(new ShopByNameModel("Categories"));

        shopByNameModels.add(new ShopByNameModel("Brands"));

        shopByNameModels.add(new ShopByNameModel("Makes"));

        rv_shopby.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        rv_shopby.setMotionEventSplittingEnabled(false);

        //int size =3;

        rv_shopby.setItemAnimator(new DefaultItemAnimator());

        ShopbyAdapter shopbyAdapter = new ShopbyAdapter(getContext(), shopByNameModels);

        rv_shopby.setAdapter(shopbyAdapter);

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