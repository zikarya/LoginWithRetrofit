package com.example.zikar.myorders.User;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zikar.myorders.R;
import com.example.zikar.myorders.Utils.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zikar.myorders.Utils.Constants.Company;
import static com.example.zikar.myorders.Utils.Constants.Email;
import static com.example.zikar.myorders.Utils.Constants.MyPREFERENCES;
import static com.example.zikar.myorders.Utils.Constants.Name;
import static com.example.zikar.myorders.Utils.Constants.Phone;
import static com.example.zikar.myorders.Utils.Constants.Pwd;

public class RegisterUser extends AppCompatActivity{
    Context context;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        context = this;
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        register();
    }
    private void register() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        (findViewById(R.id.req_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User newUser = new User(0, ((EditText) findViewById(R.id.name_field)).getText().toString(),
                        ((EditText) findViewById(R.id.company_field)).getText().toString(),
                        ((EditText) findViewById(R.id.phone_field)).getText().toString(),
                        ((EditText) findViewById(R.id.email_field)).getText().toString(),
                        ((EditText) findViewById(R.id.password_field)).getText().toString(), "", 0);
                setSharedPref(newUser);
                if (validateForm(newUser)) {
                    boolean success = false;
                    //TODO: CREATE ApiClient to (success = registeruser();)
                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    if (!success) {
                        //TODO: output "user already exists" snackbar
                        return;
                    } else {
                        //TODO: "user registered successfully" snackbar
                        Snackbar.make(v, "Request sent to admin 2", Snackbar.LENGTH_LONG).show();
                        return;
                    }
                } else {
                    Snackbar.make(v, "Invalid details", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    protected boolean validateForm(User u){
        if(u.getUserName().trim()!=null &&
                u.getCompanyName().trim()!=null &&
                u.getPhone().trim() !=null &&
                u.getEmailAddress().trim() !=null &&
                u.getPwd().trim() !=null){
            Log.d("validateForm","true");
            return true;
        }
        Log.d("validateForm","false");
        return false;
    }


    private void setSharedPref(User newUser) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, newUser.getUserName());
        editor.putString(Company, newUser.getCompanyName());
        editor.putString(Phone, newUser.getPhone());
        editor.putString(Email, newUser.getEmailAddress());
        editor.putString(Pwd, newUser.getPwd());
        editor.commit();
    }
}
