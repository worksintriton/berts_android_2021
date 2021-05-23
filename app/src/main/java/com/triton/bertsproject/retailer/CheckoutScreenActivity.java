package com.triton.bertsproject.retailer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.triton.bertsproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckoutScreenActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_proceed)
    LinearLayout ll_proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_screen);

        ButterKnife.bind(this);

        ll_proceed.setOnClickListener(v -> startActivity(new Intent(CheckoutScreenActivity.this,OrderListActivity.class)));

    }
}