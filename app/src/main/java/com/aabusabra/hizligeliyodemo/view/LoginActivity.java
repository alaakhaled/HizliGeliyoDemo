package com.aabusabra.hizligeliyodemo.view;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.aabusabra.hizligeliyodemo.R;
import com.aabusabra.hizligeliyodemo.utils.Utils;
import com.aabusabra.hizligeliyodemo.widgets.CustomAlertDialog;



public class LoginActivity extends AppCompatActivity {

    private String TAG = LoginActivity.class.getSimpleName();
    private Context context;

    private Button loginbtn;



    //set font
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        Utils.overrideFonts(getApplicationContext(), getWindow().getDecorView().getRootView());
        context = getApplicationContext();



        //hide keyboard when click at anyplace inside window
        Utils.setupKeyboardHider(LoginActivity.this, getWindow().getDecorView().getRootView());


        loginbtn = findViewById(R.id.loginbtn);







        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Utils.haveNetworkConnection(context))
                {
                    final CustomAlertDialog alertDialog = new CustomAlertDialog(LoginActivity.this);
                    alertDialog.setText(getResources().getString(R.string.no_internet_connection_msg));

                    alertDialog.setPositiveButton(getResources().getString(R.string.ok), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                            finish();

                        }
                    });

                    alertDialog.show();
                }
                else{
                    Intent i = new Intent(LoginActivity.this, ProductActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        });



    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}