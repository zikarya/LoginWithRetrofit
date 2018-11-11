package com.example.zikar.myorders.Objects;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.zikar.myorders.R;
import com.example.zikar.myorders.User.LoginUser;
import com.example.zikar.myorders.User.RegisterUser;
import com.example.zikar.myorders.User.User;


public class PlaceNewOrder extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        processOrder();
    }

    private void processOrder(){
        User loggedIn = new User().getLoggedInUser(PlaceNewOrder.this);
    }

}
