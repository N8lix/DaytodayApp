package com.example.daytodayapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
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
    private RecyclerView recyclerView2;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mAdapter2;
    private ArrayList<items> itemsArrayList;
    private ArrayList<items> itemsArrayList2;

    private DatabaseHelper mydb;
    private int toggle =0;
    private int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        mydb = new DatabaseHelper(this);
        mydb.adddatabase();
        SharedPreferences mpref = getSharedPreferences("label",0);
        String mString = mpref.getString("tag","0");

        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        int myIntValue = sp.getInt("your_int_key", -1);
        score = myIntValue;
        ((TextView)findViewById(R.id.my_number)).setText(String.valueOf(score));





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

        recyclerView2 = (RecyclerView) findViewById(R.id.my_item2);
        mAdapter2 = new MyAdapter(itemsArrayList2 , this);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(Task.this);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(mAdapter2);
        Log.d("masuk","start");


    }

    void showtask() {

        itemsArrayList = new ArrayList<>();
        Cursor res = mydb.getAllData();
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("2" + res.getString(0) + "\n");
            buffer.append("3" + res.getString(1) + "\n");
            buffer.append("4" + res.getString(2) + "\n");
            buffer.append("5" + res.getString(3) + "\n");
          //  itemsArrayList.add(new items(res.getString(0), res.getString(1), "Tap if to get points"));
            itemsArrayList.add(new items(res.getString(0), res.getString(1), "Click to Complete"));
            itemsArrayList2.add(new items(res.getString(0), res.getString(1), "Click to Complete"));
        }

        // Show

    }

//    void resetscore(View view){
//        Cursor res = mydb.reset();
//
//    }


    @Override
    protected void onStart() {
        super.onStart();
        start();
    }

    @Override
    public void onItemClick(int position) {
        String name = itemsArrayList.get(position).getName();
        score += Integer.parseInt(itemsArrayList.get(position).getPoint());
        showMessage(name,"text");
        mydb.done(name,Integer.parseInt(itemsArrayList.get(position).getPoint()));
        ((TextView)findViewById(R.id.my_number)).setText(String.valueOf(score));

        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("your_int_key", score);
        editor.commit();

        itemsArrayList.remove(position);
        start();
    }


//    public void eraseAll(View view) {
//        mydb.eraserhead();
//    }


    public void Allmi(View view) {
         mydb.adddatabase();
        Cursor res = mydb.getAllData();
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("1" + res.getString(0) + "\n");
            buffer.append("2" + res.getString(1) + "\n");
            buffer.append("3" + res.getString(2) + "\n");
            buffer.append("4" + res.getString(3) + "\n\n");
        }

        // Show all data
        start();
        showMessage("Data", buffer.toString());
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void clicknext(View v){
        Intent intent = new Intent (this, Spendpoints.class);
        startActivity(intent);
    }

}
