package com.example.zikar.myorders.User;
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
import static com.example.zikar.myorders.Utils.Constants.Access;
import static com.example.zikar.myorders.Utils.Constants.Company;
import static com.example.zikar.myorders.Utils.Constants.Email;
import static com.example.zikar.myorders.Utils.Constants.ID;
import static com.example.zikar.myorders.Utils.Constants.MyPREFERENCES;
import static com.example.zikar.myorders.Utils.Constants.Name;
import static com.example.zikar.myorders.Utils.Constants.OrderCount;
import static com.example.zikar.myorders.Utils.Constants.Phone;
import static com.example.zikar.myorders.Utils.Constants.Pwd;
public class UserLoggedIn extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        final TextView tvName = findViewById(R.id.name);
        final TextView tvCompany = findViewById(R.id.company);
        SharedPreferences userDetails = getSharedPreferences(MyPREFERENCES , MODE_PRIVATE);
        tvName.setText(userDetails.getString(Name, ""));
        tvCompany.setText(userDetails.getString(Company, ""));
        final Button logoutButton = findViewById(R.id.logoutButton);
        final TabLayout userTabLayout =findViewById(R.id.userTabs);
        final ViewPager userVP = findViewById(R.id.userTLContainer);
        final SectionsPagerAdapter userSPAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        userVP.setAdapter(userSPAdapter);
        userTabLayout.setupWithViewPager(userVP);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
                finish();
            }
            private void logout() {
                SharedPreferences loggedInUserSP = getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
                SharedPreferences.Editor edit = loggedInUserSP.edit();
                edit.putString(Email, "");
                edit.putString(Pwd, "");
                edit.putString(Company, "");
                edit.putString(Phone, "");
                edit.putInt(ID, 0);
                edit.putString(Name, "");
                edit.putString(Access,"");
                edit.putInt(OrderCount, 0);
                edit.commit();

            }
        });
    }

    public static class newOrderFragment extends Fragment {
        static Bundle args;
        static newOrderFragment fragment;
        public static newOrderFragment orderInstance(int sectionNumber) {
            fragment = new newOrderFragment();
            args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.a_user_new_order, container, false);
            (rootView.findViewById(R.id.submit)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ocv", "1");
                }
            });
            return rootView;
        }
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) { }
    }

    public static class PreviousOrderFrag extends Fragment implements AdapterView.OnItemSelectedListener{
        static PreviousOrderFrag fragment;
        static Bundle args;
        public static PreviousOrderFrag historyInstance(int sectionNumber) {
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

        public SectionsPagerAdapter(FragmentManager fm) { super(fm);fragmentManager = fm; }

        @Override
        public Fragment getItem(int position) {
            if (position==0){return newOrderFragment.orderInstance((position + 1)); }
            else{return PreviousOrderFrag.historyInstance((position + 1));} }

        @Override
        public int getCount() {return 2;}

        @Override
        public CharSequence getPageTitle(int position) {
            TabLayout tabLayout = findViewById(R.id.userTabs);
            switch (position) {
                case 0:return "New Order";
                case 1: return "Previous Orders";}
            return null;}
    }
}
