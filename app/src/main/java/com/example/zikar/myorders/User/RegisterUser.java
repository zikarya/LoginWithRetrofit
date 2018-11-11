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
import com.example.zikar.myorders.Utils.ApiClientUsersDB;
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

public class RegisterUser extends AppCompatActivity{
    Context context;
    String e, pwd, phone, name, company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        context = this;
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        register();
    }

    private void register(){
        //SET LISTENER TO THE REGISTER BUTTON
        findViewById(R.id.req_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm();
                ApiInterface apiInterface = ApiClientUsersDB.getClient().create(ApiInterface.class);
                Map<String,String> searchFilters = new HashMap<>();
                searchFilters.put("email",e);
                searchFilters.put("pwd",pwd);
                searchFilters.put("name",name);
                searchFilters.put("company",company);
                searchFilters.put("phone",phone);
                Call<ResponseBody> call = apiInterface.userRegister(Collections.unmodifiableMap(searchFilters));
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String loggedInString = (new JSONObject(response.body().string()))
                                    .getJSONArray("body")
                                    .getJSONObject(0)
                                    .toString();
                            User requestLogin = (new Gson()).fromJson(loggedInString, User.class);
                            Intent in = new Intent(context,RequestForLoginSent.class);
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
    }

    protected boolean validateForm(){
        e  = ((EditText)findViewById(R.id.email_field)).getText().toString();
        pwd  = ((EditText)findViewById(R.id.password_field)).getText().toString();
        name  = ((EditText)findViewById(R.id.name_field)).getText().toString();
        company  = ((EditText)findViewById(R.id.company_field)).getText().toString();
        phone  = ((EditText)findViewById(R.id.phone_field)).getText().toString();
        if(e.trim() !=null
                && pwd.trim() !=null
                && name.trim() !=null
                && company.trim() !=null
                && phone.trim() !=null){
            return true;
        }
        return false;
    }

}

