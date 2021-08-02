package com.dci.berts.retailer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.dci.berts.R;
import com.dci.berts.sessionmanager.SessionManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.dd4you.appsconfig.DD4YouConfig;

public class AboutUsActivity extends AppCompatActivity {

    private static final String TAG = "AboutUsActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.spin_kit_loadingView)
    SpinKitView spin_kit_loadingView;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_toolbar_title)
    TextView txt_toolbar_title;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    DD4YouConfig dd4YouConfig;

    SessionManager sessionManager;

    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        ButterKnife.bind(this);

        Log.w("oncreate",TAG);

        spin_kit_loadingView.setVisibility(View.GONE);

        dd4YouConfig = new DD4YouConfig(this);

        sessionManager=new SessionManager(this);

        HashMap<String, String> user = sessionManager.getProfileDetails();

        userid = user.get(SessionManager.KEY_ID);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });

        txt_toolbar_title.setText("About US");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}