package com.example.zikar.myorders.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

public class Order implements Parcelable {
    private int orderID;
    private String orderDate;
    private int userID;
    private ArrayList<StockItem> orderItems = new ArrayList<>();
    private String orderNotes;
    public Order(int oID,
                 String oDate,
                 int uID,
                 ArrayList<StockItem> oItems,
                 String oNotes) {
        this.orderID = oID;
        this.orderDate = oDate;
        this.userID = uID;
        this.orderItems = oItems;
        this.orderNotes = oNotes; }

    public Order(Parcel in) {
        this.orderID = in.readInt();
        this.orderDate = in.readString();
        this.userID = in.readInt();
        this.orderItems = in.createTypedArrayList(StockItem.CREATOR);
        this.orderNotes = in.readString();
    }

    public Order(){}

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(orderID);
        out.writeString(orderDate);
        out.writeInt(userID);
        out.writeTypedList(orderItems);
        out.writeString(orderNotes);}
    @Override
    public int describeContents() {return hashCode();}

    public static final Parcelable.Creator<Order> CREATOR = new Creator(){
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);}
        @Override
        public Order[] newArray(int size) {
            return new Order[size];}
    };

    //SET METHODS
    public void setOrderID(int oID){this.orderID = oID;}
    public void setOrderDate(String oDate){this.orderDate =  oDate;}
    public void setOrderNotes(String oNotes){this.orderNotes = oNotes;}
    //GET METHODS
    public int    getOrderId(){return this.orderID;}
    public String getOrderDate(){return this.orderDate;}  //GETS DATE AS STRING
    public Date getDate(){
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        java.util.Date date = null;
        try {
            date = format.parse(this.orderDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }  //GETS DATE AS dd-MM-yyyy format
    public String getOrderStartDate(){return this.orderDate;}
    public String getOrderIDList(){
        String idList ="";
        Iterator<StockItem> i = this.orderItems.iterator();
        while (i.hasNext()){
            idList = idList + Integer.toString(i.next().getItemId());
        }
        return idList;
    }
    public String getOrderNotes(){return this.orderNotes;}

}

