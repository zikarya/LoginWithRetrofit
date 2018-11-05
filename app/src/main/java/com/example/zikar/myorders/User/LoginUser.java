package com.example.zikar.myorders.User;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.zikar.myorders.R;
import com.example.zikar.myorders.Utils.ApiClient;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zikar.myorders.Utils.Constants.Access;
import static com.example.zikar.myorders.Utils.Constants.Company;
import static com.example.zikar.myorders.Utils.Constants.Email;
import static com.example.zikar.myorders.Utils.Constants.ID;
import static com.example.zikar.myorders.Utils.Constants.MyPREFERENCES;
import static com.example.zikar.myorders.Utils.Constants.Name;
import static com.example.zikar.myorders.Utils.Constants.OrderCount;
import static com.example.zikar.myorders.Utils.Constants.Phone;
import static com.example.zikar.myorders.Utils.Constants.Pwd;

public class LoginUser extends AppCompatActivity{
    Context context;
    SharedPreferences sharedpreferences;
    String e ;
    String pwd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        context = this;
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        login();
    }

    private void login(){
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //SET LISTENER TO THE LOGIN BUTTON
        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm();
                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Map<String,String> searchFilters = new HashMap<>();
                searchFilters.put("email",e);
                searchFilters.put("pwd",pwd);
                Call<ResponseBody> call = apiInterface.userLogin(Collections.unmodifiableMap(searchFilters));
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String loggedInString = (new JSONObject(response.body().string()))
                                            .getJSONArray("body")
                                            .getJSONObject(0)
                                            .toString();
                            User loggedIn = (new Gson()).fromJson(loggedInString, User.class);
                            setSharedPref(loggedIn);
                            Intent in = new Intent(context,UserActivity.class);
                            startActivity(in);
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("onFailure",t.getMessage());
                    }
                });
            }
        });

        //SET LISTENER TO THE REGISTER BUTTON
        ((Button)findViewById(R.id.reg_req)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginUser.this, RegisterUser.class);
                startActivityForResult(in,0);
            }
        });
    }

    protected boolean validateForm(){
        e  = ((EditText)findViewById(R.id.email_field)).getText().toString();
        pwd  = ((EditText)findViewById(R.id.password_field)).getText().toString();
        if(e.trim() !=null && pwd.trim() !=null){
            return true;
        }
        return false;
    }

    private void setSharedPref(User user){
        SharedPreferences loggedInUserSP = context.getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor edit = loggedInUserSP.edit();
        edit.putString(Email, user.getEmailAddress());
        edit.putString(Pwd, user.getPwd());
        edit.putString(Company, user.getCompanyName());
        edit.putString(Phone, user.getPhone());
        edit.putInt(ID, user.getUserId());
        edit.putString(Name, user.getUserName());
        edit.putString(Access,user.getUserAccess());
        edit.putInt(OrderCount, user.getCurrentOrderCount());
        edit.commit();
    }
}

