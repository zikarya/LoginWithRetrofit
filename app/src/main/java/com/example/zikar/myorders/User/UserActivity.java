package com.example.zikar.myorders.User;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import com.example.zikar.myorders.Objects.Order;
import com.example.zikar.myorders.R;
import static com.example.zikar.myorders.Utils.Constants.ARG_SECTION_NUMBER;
import static com.example.zikar.myorders.Utils.Constants.Name;


public class UserActivity extends AppCompatActivity {
    static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        final TextView tvName = (TextView) findViewById(R.id.name);
        final TextView tvCompany = (TextView)findViewById(R.id.company);
        SharedPreferences userDetails = this.getSharedPreferences("userdetails", MODE_PRIVATE);
      //  getSharedPreferences(Name, userDetails.getString(Name, ""));
        //tvName.setText()
        tvCompany.setText(user.getCompanyName());
        final Button logoutButton = (Button) findViewById(R.id.logoutButton);
        final TabLayout userTabLayout = (TabLayout) findViewById(R.id.userTabs);
        final ViewPager userVP = (ViewPager) findViewById(R.id.userTLContainer);
        final SectionsPagerAdapter userSPAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        userVP.setAdapter(userSPAdapter);
        userTabLayout.setupWithViewPager(userVP);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static class newOrderFragment extends Fragment {
        static Bundle args;
        static newOrderFragment fragment;
        public static newOrderFragment attendeeInstance(int sectionNumber) {
            fragment = new newOrderFragment();
            args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.a_user_new_order, container, false);
            return rootView;
        }
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) { }
    }

    public static class PreviousOrderFrag extends Fragment implements AdapterView.OnItemSelectedListener{
        static PreviousOrderFrag fragment;
        static Bundle args;
        public static PreviousOrderFrag reminderInstance(int sectionNumber) {
            fragment = new PreviousOrderFrag();
            args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.a_user_previous_orders, container, false);
            final Order order = new Order();
            return rootView;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        FragmentManager fragmentManager;
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            fragmentManager = fm;
        }

        @Override
        public Fragment getItem(int position) {
            if (position==0) {
                return newOrderFragment.attendeeInstance((position + 1)); }
            else{
                return PreviousOrderFrag.reminderInstance((position + 1));} }

        @Override
        public int getCount() {return 2;}

        @Override
        public CharSequence getPageTitle(int position) {
            TabLayout tabLayout = (TabLayout)findViewById(R.id.userTabs);
            switch (position) {
                case 0:return "New Order";
                case 1: return "Previous Orders";}
            return null;}

    }


}
