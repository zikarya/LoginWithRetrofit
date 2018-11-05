package com.example.zikar.myorders;

import android.support.v4.app.Fragment ;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.zikar.myorders.Utils.Constants;

public class ProfileFragment extends Fragment {
    private TextView tv_name,tv_email;
    private SharedPreferences pref;
    private AppCompatButton btn_change_password,btn_logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout._____logged_in,container,false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        pref = getActivity().getPreferences(0);
        tv_name.setText("Welcome : "+pref.getString(Constants.NAME,""));
        tv_email.setText(pref.getString(Constants.EMAIL,""));
    }


    private void initViews(View view){
    }
//        goToLogin();
    }

