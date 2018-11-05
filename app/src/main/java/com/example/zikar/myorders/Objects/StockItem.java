package com.example.zikar.myorders.Objects;

import android.os.Parcel;
import android.os.Parcelable;

public class StockItem implements Parcelable {
    private int itemID;
    private String itemName;
    private String itemNotes;
    private String itemPrice;
    public StockItem(int iID,
                     String iName,
                     String iDescription,
                     String iPrice){
        this.itemID = iID;
        this.itemName = iName;
        this.itemNotes = iDescription;
        this.itemPrice = iPrice;
        }

    public StockItem(Parcel in) {
        this.itemID = in.readInt();
        this.itemName = in.readString();
        this.itemNotes = in.readString();
        this.itemPrice = in.readString();
    }

    public StockItem(){}

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(itemID);
        out.writeString(itemName);
        out.writeString(itemNotes);
        out.writeString((itemPrice));}
    @Override
    public int describeContents() {return hashCode();}

    public static final Creator CREATOR = new Creator(){
        @Override
        public StockItem createFromParcel(Parcel in) {
            return new StockItem(in);}
        @Override
        public StockItem[] newArray(int size) {
            return new StockItem[size];}
    };

    //SET METHODS
    public void setItemID(int eID){this.itemID = eID;}
    public void setItemName(String iName){this.itemName=iName;}
    public void setItemNotes(String iNotes){this.itemNotes = iNotes;}
    public void setItemPrice(String iPrice){this.itemPrice = iPrice;}
    //GET METHODS
    public int getItemId(){return this.itemID;}
    public String getItemName(){return this.itemName;}
    public String getItemNotes(){return this.itemNotes;}
    public String getItemPrice() {return itemPrice;}
    //UTILITY METHODS

}

