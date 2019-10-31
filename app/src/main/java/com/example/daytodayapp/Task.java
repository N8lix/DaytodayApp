package com.example.daytodayapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class Task extends AppCompatActivity implements MyAdapter.OnItemListener {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<items> itemsArrayList;

    private DatabaseHelper mydb;
    private int toggle =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        mydb = new DatabaseHelper(this);

        SharedPreferences mpref = getSharedPreferences("label",0);
        String mString = mpref.getString("tag","0");






        //  Intent intent = getIntent();
        //  String username = intent.getStringExtra(MainActivity.Extra_message);

        showtask();
        start();


    }

    void start(){
        recyclerView = (RecyclerView) findViewById(R.id.my_item);
        mAdapter = new MyAdapter(itemsArrayList , this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Task.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        Log.d("masuk","start");

    }

    void showtask() {

        itemsArrayList = new ArrayList<>();
        Cursor res = mydb.getAllData();
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("1" + res.getString(0) + "\n");
            buffer.append("2" + res.getString(1) + "\n");
            buffer.append("3" + res.getString(2) + "\n");;
            itemsArrayList.add(new items(res.getString(0), res.getString(1), res.getString(2)));
        }

        // Show

    }

    void showscore(){

    }

    @Override
    public void onItemClick(int position) {
        String name = itemsArrayList.get(position).getName();
        int score = Integer.parseInt(itemsArrayList.get(position).getPoint());
        showMessage(name,"text");
        mydb.done(name);
        SharedPreferences mpref = getSharedPreferences("label",0);
        String mString = mpref.getString("tag","0");
        SharedPreferences.Editor mEditor = mpref.edit();
        mEditor.putString("tag", String.valueOf(score));

        itemsArrayList.remove(position);
        start();
    }


//    public void eraseAll(View view) {
//        mydb.eraserhead();
//    }


//    public void Allmi(View view) {
//        mydb.adddatabase();
//        Cursor res = mydb.getAllData();
//        StringBuffer buffer = new StringBuffer();
//        while (res.moveToNext()) {
//            buffer.append("1" + res.getString(0) + "\n");
//            buffer.append("2" + res.getString(1) + "\n");
//            buffer.append("3" + res.getString(2) + "\n");
//            buffer.append("4" + res.getString(3) + "\n\n");
//        }
//
//        // Show all data
//        showMessage("Data", buffer.toString());
//    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
