package com.dci.berts.retailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dci.berts.R;
import com.dci.berts.adapter.NotificationDashboardAdapter;
import com.dci.berts.api.APIClient;
import com.dci.berts.api.RestApiInterface;
import com.dci.berts.requestpojo.NotificationGetlistRequest;
import com.dci.berts.requestpojo.NotificationsMarkRequest;
import com.dci.berts.responsepojo.NotificationGetlistResponse;
import com.dci.berts.responsepojo.SuccessResponse;
import com.dci.berts.sessionmanager.SessionManager;
import com.dci.berts.utils.RestUtils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {

    private static final String TAG = "NotificationActivity";

    Context context = NotificationActivity.this;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_notificationlist)
    RecyclerView rvnotifiaction;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView tvNorecords;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    String fromactivity;

    SessionManager session;
    String userid = "";
    private List<NotificationGetlistResponse.DataBean> notificationGetlistResponseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ButterKnife.bind( this);
        Log.w("Oncreate", TAG);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fromactivity = extras.getString("fromactivity");
        }
        spin_kit_loadingView.setVisibility(View.GONE);

        img_back.setOnClickListener(v -> {

            onBackPressed();

        });

        notificationGetlistResponseList = new ArrayList<>();

        notificationGetlistResponseList.add(new NotificationGetlistResponse.DataBean("610bc34d3f6d40089bb13109","60e58359d6f9cc31c836153d","New Orders","You have an orders OID-1628160845295 at 06-08-2021 12:30 AM",APIClient.BASE_IMAGE_URL,"","06-08-2021 12:30 AM"));

        notificationGetlistResponseList.add(new NotificationGetlistResponse.DataBean("610bc34d3f6d40089bb13109","60e58359d6f9cc31c836153d","New Orders","You have an orders OID-1628160845295 at 06-08-2021 12:30 AM",APIClient.BASE_IMAGE_URL,"","06-08-2021 12:30 AM"));

        notificationGetlistResponseList.add(new NotificationGetlistResponse.DataBean("610bc34d3f6d40089bb13109","60e58359d6f9cc31c836153d","New Orders","You have an orders OID-1628160845295 at 06-08-2021 12:30 AM",APIClient.BASE_IMAGE_URL,"","06-08-2021 12:30 AM"));

        notificationGetlistResponseList.add(new NotificationGetlistResponse.DataBean("610bc34d3f6d40089bb13109","60e58359d6f9cc31c836153d","New Orders","You have an orders OID-1628160845295 at 06-08-2021 12:30 AM",APIClient.BASE_IMAGE_URL,"","06-08-2021 12:30 AM"));

        notificationGetlistResponseList.add(new NotificationGetlistResponse.DataBean("610bc34d3f6d40089bb13109","60e58359d6f9cc31c836153d","New Orders","You have an orders OID-1628160845295 at 06-08-2021 12:30 AM",APIClient.BASE_IMAGE_URL,"","06-08-2021 12:30 AM"));

        notificationGetlistResponseList.add(new NotificationGetlistResponse.DataBean("610bc34d3f6d40089bb13109","60e58359d6f9cc31c836153d","New Orders","You have an orders OID-1628160845295 at 06-08-2021 12:30 AM",APIClient.BASE_IMAGE_URL,"","06-08-2021 12:30 AM"));


        setView();
    }

    @SuppressLint("LogNotTimber")
    private void notificationMarkResponseCall() {
        spin_kit_loadingView.setVisibility(View.VISIBLE);
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<SuccessResponse> call = ApiService.notificationMarkResponseCall(RestUtils.getContentType(),notificationsMarkRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<SuccessResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<SuccessResponse> call, @NonNull Response<SuccessResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"notificationMarkResponseCall SuccessResponse"+ "--->" + new Gson().toJson(response.body()));

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                    }

                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<SuccessResponse> call, @NonNull Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);

                Log.w(TAG,"notificationMarkResponseCall SuccessResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private NotificationsMarkRequest notificationsMarkRequest() {
        /*
         * user_id : 5ee3666a5dfb34019b13c3a2
         */
        NotificationsMarkRequest notificationsMarkRequest = new NotificationsMarkRequest();
        notificationsMarkRequest.setUser_id(userid);
        Log.w(TAG,"notificationsMarkRequest"+ "--->" + new Gson().toJson(notificationsMarkRequest));
        return notificationsMarkRequest;
    }


    private void setView() {
        rvnotifiaction.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvnotifiaction.setItemAnimator(new DefaultItemAnimator());
        NotificationDashboardAdapter notificationDashboardAdapter = new NotificationDashboardAdapter(getApplicationContext(), notificationGetlistResponseList);
        rvnotifiaction.setAdapter(notificationDashboardAdapter);

    }

    @SuppressLint("LogNotTimber")
    private void notificationGetlistResponseCall() {
        spin_kit_loadingView.setVisibility(View.VISIBLE);
        RestApiInterface ApiService = APIClient.getClient().create(RestApiInterface.class);
        Call<NotificationGetlistResponse> call = ApiService.notificationGetlistResponseCall(RestUtils.getContentType(),notificationGetlistRequest());
        Log.w(TAG,"url  :%s"+ call.request().url().toString());

        call.enqueue(new Callback<NotificationGetlistResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<NotificationGetlistResponse> call, @NonNull Response<NotificationGetlistResponse> response) {
                spin_kit_loadingView.setVisibility(View.GONE);
                Log.w(TAG,"NotificationGetlistResponse"+ "--->" + new Gson().toJson(response.body()));

                if (response.body() != null) {
                    if(response.body().getCode() == 200){
                        notificationMarkResponseCall();

                        if(response.body().getData() != null && response.body().getData().size()>0){
                            notificationGetlistResponseList = response.body().getData();
                            tvNorecords.setVisibility(View.GONE);
                            rvnotifiaction.setVisibility(View.VISIBLE);
                            setView();
                        }else{
                            rvnotifiaction.setVisibility(View.GONE);
                            tvNorecords.setVisibility(View.VISIBLE);
                            tvNorecords.setText(getResources().getString(R.string.no_notifications));

                        }


                    }

                }


            }

            @SuppressLint("LogNotTimber")
            @Override
            public void onFailure(@NonNull Call<NotificationGetlistResponse> call, @NonNull Throwable t) {
                spin_kit_loadingView.setVisibility(View.GONE);

                Log.w(TAG,"NotificationGetlistResponse"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private NotificationGetlistRequest notificationGetlistRequest() {
        /*
         * user_id : 5ee3666a5dfb34019b13c3a2
         */
        NotificationGetlistRequest notificationGetlistRequest = new NotificationGetlistRequest();
        notificationGetlistRequest.setUser_id(userid);
        Log.w(TAG,"notificationGetlistRequest"+ "--->" + new Gson().toJson(notificationGetlistRequest));
        return notificationGetlistRequest;
    }

    @Override
    public void onBackPressed() {

        callDirections("5");
    }

    public void callDirections(String tag){
        Intent intent = new Intent(NotificationActivity.this,RetailerDashboardActivity.class);
        intent.putExtra("tag",tag);
        startActivity(intent);
        finish();

    }

}