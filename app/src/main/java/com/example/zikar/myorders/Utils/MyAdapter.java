package com.example.zikar.myorders.Utils;

import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zik.orders.Objects.StockItem;
import com.example.zik.orders.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private static ListItemClickListener contactSelectListener;
        private Cursor contactCursor;
        private ArrayList<StockItem> selected;


    public interface ListItemClickListener {
            void onListItemClick(int clickedIndex, boolean isChecked);
        }

        public MyAdapter(Cursor cursor,
                         ArrayList<StockItem> alreadySelected,
                         ListItemClickListener listener){
            contactCursor = cursor;
            contactSelectListener = listener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            int layoutIdForListItem = R.layout.list_item;
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            boolean attach = false;
            View view = inflater.inflate(layoutIdForListItem, parent, attach);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }
        
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            if (!contactCursor.moveToPosition(position)) return;
            StockItem stockItem = new StockItem(1,"bed sheet", "none", "0.25");
            holder.nameView.setText(stockItem.getItemName() );
            holder.itemView.setTag(position);
            if (selected !=null){
                for (StockItem i: selected){
                    if (i.getItemId() == stockItem.getItemId()){
                        holder.nameView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorAccent));
                    }
                }}
        }

        @Override
        public int getItemCount() {
            return contactCursor.getCount();
        }

        public void swapCursor(Cursor newCursor) {
            if (contactCursor != null) contactCursor.close();
            contactCursor = newCursor;
            if (newCursor != null) {
                this.notifyDataSetChanged();
            }
        }

        public Cursor getCursor() {
            Cursor returnedCursor = contactCursor;
            return returnedCursor;
        }

        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            final TextView nameView;
            boolean isSelected = false;
            public MyViewHolder(View itemView) {
                super(itemView);
                nameView = (TextView) itemView.findViewById(R.id.tv_item_number);
                nameView.setOnClickListener(this);
            }
            @Override
            public void onClick(View v) {
                if (isSelected) {
                    isSelected = false;
                    nameView.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.colorWhite));
                } else {
                    isSelected = true;
                    nameView.setBackgroundColor(ContextCompat.getColor(v.getContext(), R.color.colorDashboard));
                }
                contactSelectListener.onListItemClick(getAdapterPosition(), isSelected);
            }
        }
    }

