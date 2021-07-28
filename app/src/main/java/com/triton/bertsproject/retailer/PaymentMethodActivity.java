package com.triton.bertsproject.retailer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.triton.bertsproject.R;
import com.triton.bertsproject.sessionmanager.Connectivity;
import com.triton.bertsproject.sessionmanager.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import in.dd4you.appsconfig.DD4YouConfig;

public class PaymentMethodActivity extends AppCompatActivity {

    private static final String TAG = "PaymentMethodActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rdGroupPayment)
    RadioGroup rdGroupPayment;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rdpaymgtwy)
    RadioButton rdpaymgtwy;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rdcreditlimit)
    RadioButton rdcreditlimit;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rdcod)
    RadioButton rdcod;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_payment_gateway)
    Button btn_payment_gateway;

    SessionManager sessionManager;

    String user_id,user_type,radioValue="",selected_method="";

    RadioButton radioButton;

    String brand_id,brand_name,parent_id,subcategid,subcategname,make_id,model_id,model_name;

    String prod_id,prod_name,shipid;

    String addr_name,address1,state_name,country_name;

    Connectivity connectivity;

    String value,categ_name,make_name,search_text,fromactivity ;

    JSONObject data ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(this);

        Log.w("Oncreate", TAG);

        HashMap<String, String> user = sessionManager.getProfileDetails();

        user_id = user.get(SessionManager.KEY_ID);

        user_type = user.get(SessionManager.KEY_TYPE);


        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            radioValue=extras.getString("radioValue");

            fromactivity = extras.getString("fromactivity");

            try {

                if(getIntent().getStringExtra("data")!=null){

                    data = new JSONObject(getIntent().getStringExtra("data"));
                }

                else {

                    data = new JSONObject();

                    Log.w(TAG,"Cond --> false");

                    data.put("sample","0");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            prod_id = extras.getString("prod_id");

            //prod_id = "2";

            prod_name = extras.getString("prod_name");

            brand_id = extras.getString("brand_id");

            brand_name = extras.getString("brand_name");

            parent_id = extras.getString("parent_id");

            categ_name = extras.getString("categ_name");

            subcategid = extras.getString("subcategid");

            subcategname = extras.getString("subcategname");

            make_id = extras.getString("make_id");

            make_name = extras.getString("make_name");

            model_id = extras.getString("model_id");

            model_name = extras.getString("model_name");

            search_text = extras.getString("search_text");

            Log.w(TAG,"Connectivity fromactivity "+ fromactivity + "brand_id : "+brand_id + "brand_name : "+brand_name+"parent_id : "+parent_id+ "subcategid :" +subcategid

                    + "subcategname : "+subcategname + "make_id : "+make_id + "model_id :" +model_id

                    + "model_name : "+model_name + "search_text "+search_text);

        }

        RadioGroup rdGroupPayment = (RadioGroup) findViewById(R.id.rdGroupPayment);

        rdGroupPayment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
              radioButton = (RadioButton) findViewById(checkedId);

                selected_method = radioButton.getText().toString();
            }
        }
        );

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        if(user_type!=null){

            if(user_type.equals("retail")){

                rdcreditlimit.setVisibility(View.GONE);

            }

        }




        btn_payment_gateway.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               onBackPressed();

            }

        });



    }

    @Override
    public void onBackPressed() {

        if(!selected_method.equals("")){

            gotoCheckoutActiVity();
        }

        else {

            Toasty.error(PaymentMethodActivity.this, "Please Select Payment Method", Toast.LENGTH_SHORT).show();
        }
    }

    public void gotoCheckoutActiVity(){

        Intent intent=new Intent(PaymentMethodActivity.this,CheckoutScreenActivity.class);

        intent.putExtra("radioValue",selected_method);

        intent.putExtra("fromactivity",TAG);

        intent.putExtra("prod_id",prod_id);

        intent.putExtra("prod_name",prod_name);

        intent.putExtra("brand_id",brand_id);

        intent.putExtra("brand_name",brand_name);

        intent.putExtra("parent_id",parent_id);

        intent.putExtra("categ_name",categ_name);

        intent.putExtra("subcategid",subcategid);

        intent.putExtra("subcategname",subcategname);

        intent.putExtra("make_id",make_id);

        intent.putExtra("make_name",make_name);

        intent.putExtra("model_id", model_id);

        intent.putExtra("model_name",model_name);

        intent.putExtra("search_text",search_text);

        intent.putExtra("data",data.toString());

        startActivity(intent);

        finish();
    }
}