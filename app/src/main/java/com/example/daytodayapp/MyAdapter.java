package com.example.daytodayapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<items> dataList;
    private OnItemListener monItemListener;



    public MyAdapter(ArrayList<items> dataList, OnItemListener onItemListener){
        this.dataList = dataList;
        this.monItemListener = onItemListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list, parent , false);
        return new MyViewHolder(view,monItemListener);
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        holder.txtName.setText(dataList.get(position).getName());
        holder.txtPoint.setText(String.valueOf(dataList.get(position).getPoint()));
        holder.txtDone.setText(dataList.get(position).getDone());
        holder.whose = dataList.get(position).getWhose();
    }


    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtName, txtPoint , txtDone;
        private int whose;
        OnItemListener onItemListener;

//        private boolean txtDone;
      //  private Button txtbutton;

        public MyViewHolder(View itemView, OnItemListener onItemListener){
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.item_name);
            txtPoint = (TextView) itemView.findViewById(R.id.item_points);
            txtDone = (TextView) itemView.findViewById(R.id.item_done);
            this.whose = whose;
            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            String name = this.txtName.getText().toString() ;
            int whose = this.whose;
            onItemListener.onItemClick(name, whose, getAdapterPosition());
        }
    }

    public interface OnItemListener{

        void onItemClick (String name, int whose, int position);
    }



}
