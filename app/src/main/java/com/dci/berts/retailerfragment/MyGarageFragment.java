package com.dci.berts.retailerfragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.dci.berts.R;
import com.dci.berts.activities.LoginActivity;
import com.dci.berts.activities.RegisterActivity;
import com.dci.berts.adapter.VehicletListAdapter;
import com.dci.berts.api.APIClient;
import com.dci.berts.api.RestApiInterface;
import com.dci.berts.interfaces.SetDefaultVehicleProductListener;
import com.dci.berts.requestpojo.SetDefaultVehicleRequest;
import com.dci.berts.requestpojo.ShowVehiclelistRequest;
import com.dci.berts.responsepojo.SetDefaultVehicleResponse;
import com.dci.berts.responsepojo.ShowVehiclelistResponse;
import com.dci.berts.retailer.AddVehicleActivity;
import com.dci.berts.retailer.RetailerCartActivity;
import com.dci.berts.retailer.RetailerDashboardActivity;
import com.dci.berts.sessionmanager.Connectivity;
import com.dci.berts.sessionmanager.SessionManager;
import com.dci.berts.utils.RestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import in.dd4you.appsconfig.DD4YouConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyGarageFragment extends Fragment implements View.OnClickListener, SetDefaultVehicleProductListener {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_addVeh)
    Button btn_addVeh;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_vehiclelist)
    RecyclerView rv_vehiclelist;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cl_loginbefore)
    CardView cl_loginbefore;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.cv_addvehicle)
    CardView cv_addvehicle;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rl_vehicle)
    RelativeLayout rl_vehicle;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_signin)
    Button btn_sigin;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_user_login)
    TextView txt_user_login;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_cart_count)
    TextView txt_cart_count;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rlcart)
    RelativeLayout rlcart;

    String cart_count ="0";


    List<ShowVehiclelistResponse.DataBean.AddvehicleBean> showvehicleBeanList ;

    String user_id,av_id;

    private static final String TAG = "MyGarageFragment";

    View view;


    private DD4YouConfig dd4YouConfig;

    AlertDialog alertDialog;

    SessionManager sessionManager;

    public MyGarageFragment() {
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
        view = inflater.inflate(R.layout.fragment_my_garage, container, false);

        ButterKnife.bind(this, view);

        Log.w("Oncreate ", TAG);

        btn_addVeh.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), AddVehicleActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeLeft(getContext());
        });

        dd4YouConfig = new DD4YouConfig(getContext());

        sessionManager=new SessionManager(getContext());

        HashMap<String, String> user = sessionManager.getProfileDetails();

        user_id = user.get(SessionManager.KEY_ID);

         // user_id  = "541";


        if (sessionManager.isLoggedIn()) {

            cl_loginbefore.setVisibility(View.GONE);

            cv_addvehicle.setVisibility(View.GONE);

            rl_vehicle.setVisibility(View.GONE);


            Connectivity connectivity = new Connectivity();

            cart_count = connectivity.getData(getContext(),"Cart_Count");

            Log.w(TAG,"cart_count "+cart_count);

            if(cart_count!=null&&!cart_count.equals("0")){

                txt_cart_count.setText(""+cart_count);
            }

            else {

                txt_cart_count.setVisibility(View.GONE);
            }

            if(dd4YouConfig.isInternetConnectivity()) {

                showvehlistResponseCall();
            }

            else {

                callnointernet();
            }
        }

        else {

            cv_addvehicle.setVisibility(View.GONE);

            rl_vehicle.setVisibility(View.GONE);

            cl_loginbefore.setVisibility(View.VISIBLE);

            txt_cart_count.setVisibility(View.GONE);

            spin_kit_loadingView.setVisibility(View.GONE);

            txt_user_login.setOnClickListener(v -> {

                Intent intent = new Intent(getContext(), LoginActivity.class);

                intent.putExtra("fromactivity",TAG);

                startActivity(intent);

                Animatoo.animateSwipeLeft(Objects.requireNonNull(getContext()));
            });

            btn_sigin.setOnClickListener(v -> {

                Intent intent = new Intent(getContext(), RegisterActivity.class);

                intent.putExtra("fromactivity",TAG);

                startActivity(intent);

                Animatoo.animateSwipeRight(Objects.requireNonNull(getContext()));

            });


        }
        rlcart.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), RetailerCartActivity.class);

            intent.putExtra("fromactivity",TAG);

            startActivity(intent);

            Animatoo.animateSwipeRight(Objects.requireNonNull(getContext()));

        });
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



    @SuppressLint("LongLogTag")
    private void showvehlistResponseCall() {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<ShowVehiclelistResponse> call = apiInterface.showvehiclelistResponseCall(RestUtils.getContentType(),showVehiclelistRequest());
        Log.w(TAG,"ShowVehiclelistResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<ShowVehiclelistResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<ShowVehiclelistResponse> call, @NonNull Response<ShowVehiclelistResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "ShowVehiclelistResponse" + new Gson().toJson(response.body()));

                        showvehicleBeanList =response.body().getData().getAddvehicle();

                        cv_addvehicle.setVisibility(View.VISIBLE);

                        rl_vehicle.setVisibility(View.VISIBLE);

                        if(showvehicleBeanList!=null&&showvehicleBeanList.size()>0){

                            rv_vehiclelist.setVisibility(View.VISIBLE);

                            txt_no_records.setVisibility(View.GONE);

                            setView(showvehicleBeanList);

                        }

                        else {

                            rv_vehiclelist.setVisibility(View.GONE);

                            txt_no_records.setVisibility(View.VISIBLE);

                            txt_no_records.setText("No Vehicles Found");
                        }
                    }

                    else {

                        showErrorLoading(response.body().getMessage());

                    }

                }



            }


            @Override
            public void onFailure(@NonNull Call<ShowVehiclelistResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"ShowVehiclelistResponse flr"+t.getMessage());
            }
        });

    }

    private void setView(List<ShowVehiclelistResponse.DataBean.AddvehicleBean> showvehicleBeanList) {

        rv_vehiclelist.setLayoutManager(new LinearLayoutManager(getContext()));

        rv_vehiclelist.setItemAnimator(new DefaultItemAnimator());

        VehicletListAdapter vehicletListAdapter = new VehicletListAdapter(getContext(), showvehicleBeanList,this);

        rv_vehiclelist.setAdapter(vehicletListAdapter);



    }

    @SuppressLint("LongLogTag")
    private ShowVehiclelistRequest showVehiclelistRequest() {

        /*
         * USER_ID : 541
         * MODE : LIST
         */


        ShowVehiclelistRequest showVehiclelistRequest = new ShowVehiclelistRequest();
        showVehiclelistRequest.setUSER_ID(user_id);
        showVehiclelistRequest.setMODE("LIST");

        Log.w(TAG,"ShowVehiclelistRequest "+ new Gson().toJson(showVehiclelistRequest));
        return showVehiclelistRequest;
    }

    private void callnointernet() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("No Internet Conncetion");
        builder.setMessage("Please Turn on Your MobileData or Connect to Wifi Network");
        builder.setCancelable(false);
        builder.setPositiveButton("RETRY", (dialogInterface, i) -> {
            callDirections("2");
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public void showErrorLoading(String errormesage){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage(errormesage);
        alertDialogBuilder.setPositiveButton("ok",
                (arg0, arg1) -> hideLoading());


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void hideLoading(){
        try {
            alertDialog.dismiss();
        }catch (Exception ignored){

        }
    }

    public void callDirections(String tag){
        Intent intent = new Intent(getContext(),RetailerDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
    }

    @Override
    public void setVehicleidListener(String id) {

        av_id = id;

        showAlert(av_id);


    }

    private void showAlert(String av_idd) {


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_vehicle_layout, null);
        dialogBuilder.setView(dialogView);

        RelativeLayout rl_yes = dialogView.findViewById(R.id.rl_yes);

        RelativeLayout rl_no = dialogView.findViewById(R.id.rl_no);

        RelativeLayout rl_cancel = dialogView.findViewById(R.id.rl_cancel);

        rl_cancel.setVisibility(View.GONE);

        TextView alert_title_txtview = dialogView.findViewById(R.id.alert_title_txtview);

        alert_title_txtview.setText("Are You Sure to set this vehicle as Default vehicle");

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        rl_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });

        rl_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dd4YouConfig.isInternetConnectivity()) {

                    setdafultvehResponseCall(av_idd);
                }

                else {

                    callnointernet();
                }

                alertDialog.dismiss();

            }
        });

    }


    @SuppressLint("LongLogTag")
    private void setdafultvehResponseCall(String av_id) {

        spin_kit_loadingView.setVisibility(View.VISIBLE);
        //Creating an object of our api interface
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<SetDefaultVehicleResponse> call = apiInterface.setdefaultVehResponseCall(RestUtils.getContentType(),setDefaultVehicleRequest(av_id));
        Log.w(TAG,"SetDefaultVehicleResponse url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SetDefaultVehicleResponse>() {
            @SuppressLint("LogNotTimber")
            @Override
            public void onResponse(@NonNull Call<SetDefaultVehicleResponse> call, @NonNull Response<SetDefaultVehicleResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);

                if (response.body() != null) {

                    if(200==response.body().getCode()) {

                        Log.w(TAG, "SetDefaultVehicleResponse" + new Gson().toJson(response.body()));

                        Toasty.success(Objects.requireNonNull(getContext()),response.body().getMessage(), Toast.LENGTH_SHORT, true).show();

                        if(dd4YouConfig.isInternetConnectivity()) {
                            showvehlistResponseCall();
                        }

                        else {

                            callnointernet();
                        }
                        }

                        else {

                        showErrorLoading(response.body().getMessage());
                        }
                    }


                }




            @Override
            public void onFailure(@NonNull Call<SetDefaultVehicleResponse> call,@NonNull  Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"SetDefaultVehicleResponse flr"+t.getMessage());
            }
        });

    }
    @SuppressLint("LongLogTag")
    private SetDefaultVehicleRequest setDefaultVehicleRequest(String av_id) {

        /*
         * AV_ID : 10
         * MODE : SETDEFAULT
         */


        SetDefaultVehicleRequest setDefaultVehicleRequest = new SetDefaultVehicleRequest();
        setDefaultVehicleRequest.setAV_ID(av_id);
        setDefaultVehicleRequest.setMODE("SETDEFAULT");

        Log.w(TAG,"SETDEFAULT "+ new Gson().toJson(setDefaultVehicleRequest));
        return setDefaultVehicleRequest;
    }

}