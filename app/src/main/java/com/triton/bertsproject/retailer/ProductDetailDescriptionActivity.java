package com.triton.bertsproject.retailer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.triton.bertsproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailDescriptionActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_addcart)
    Button btn_addcart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_description);

        ButterKnife.bind(this);

        btn_addcart.setOnClickListener(v -> startActivity(new Intent(ProductDetailDescriptionActivity.this,RetailerCartActivity.class)));

    }
}