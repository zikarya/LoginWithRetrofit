package com.example.zikar.myorders.User;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class User implements Parcelable {
        private int userId;
        @SerializedName("name")
        private String userName;
        @SerializedName("company")
        private String companyName;
        @SerializedName("number")
        private String userNumber;
        @SerializedName("email")
        private String userEmail;
        @SerializedName("pwd")
        private String userPwd;
        @SerializedName("access")
        private String userAccess;
        @SerializedName("ordercount")
        private int currentOrderCount =0;
        public User(@Nullable int uId,
                    String uName,
                    String uCompanyName,
                    String uNumber,
                    String uEmail,
                    String uPwd,
                    String uAccess,
                    int uOrderCount){
            this.userId = uId;
            this.userName = uName;
            this.companyName = uCompanyName;
            this.userNumber = uNumber;
            this.userEmail = uEmail;
            this.userPwd = uPwd;
            this.userAccess = uAccess;
            this.currentOrderCount = uOrderCount;}

        public User(Parcel in) {
            this.userId = in.readInt();
            this.userName = in.readString();
            this.companyName = in.readString();
            this.userNumber = in.readString();
            this.userEmail = in.readString();
            this.userPwd = in.readString();
            this.userAccess = in.readString();
            this.currentOrderCount = in.readInt();}

        public User(){}

        public User(String mJsonString){

        }

        @Override public void writeToParcel(Parcel out, int flags) {
            out.writeInt(userId);
            out.writeString(userName);
            out.writeString(companyName);
            out.writeString(userNumber);
            out.writeString(userEmail);
            out.writeString(userPwd);
            out.writeString(userAccess);
            out.writeInt(currentOrderCount);
        }
        @Override public int describeContents() {
            return hashCode();
        }

        public static final Creator CREATOR = new Creator(){
            @Override public User createFromParcel(Parcel in) {
                return new User(in);}
            @Override public User[] newArray(int size) {
                return new User[size];}
        };

        //SET METHODS
        public void setUserID(int userID){this.userId =userID;}
        public void setUserName(String uName){this.userName = uName;}
        public void setPhone(String uNumber){this.userNumber = uNumber;}
        public void setEmailAddress(String uEmailAddress){this.userEmail = uEmailAddress;}
        public void setPwd(String uPwd){this.userPwd = uPwd;}
        public void setUserAccess(String userAccess){this.userAccess = userAccess;}
        public void setCompanyName(String uCName){this.companyName = uCName;}
        public void setOrderCount(int uOrderCount){this.currentOrderCount = uOrderCount;}
        //GET METHODS
        public int getUserId(){return this.userId;}
        public String getUserName(){return this.userName;}
        public String getPhone(){return this.userNumber;}
        public String getEmailAddress(){return this.userEmail;}
        public String getPwd(){return this.userPwd;}
        public String getUserAccess(){return this.userAccess;}
        public String getCompanyName(){return this.companyName;}
        public int getCurrentOrderCount(){return this.currentOrderCount;}

}
