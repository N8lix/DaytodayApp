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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Task extends AppCompatActivity implements MyAdapter.OnItemListener {
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mAdapter2;
    private ArrayList<items> itemsArrayList;
    private ArrayList<items> itemsArrayList2;
    private boolean nextday = false;

    private DatabaseHelper mydb;
    private int toggle =0;
    private int score=0;
    private int score2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        mydb = new DatabaseHelper(this);

        ready();
        showtask();
        start();


    }

    @Override
    protected void onStart() {
        super.onStart();
        ready();
    }

    void ready(){
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        int myIntValue = sp.getInt("your_int_key", 0);
        int myIntValue2 = sp.getInt("your_int_key2", 0);
        score = myIntValue;
        score2 = myIntValue2;
        ((TextView)findViewById(R.id.my_number)).setText(String.valueOf(score));
        ((TextView)findViewById(R.id.my_number2)).setText(String.valueOf(score2));

        SimpleDateFormat timeStampFormat = new SimpleDateFormat("EEEE, dd-MM-yyyy HH:mm");
        Date GetDate = new Date();
        String DateStr = timeStampFormat.format(GetDate);
        ((TextView)findViewById(R.id.mydate)).setText(DateStr);

//

        mydb.eraserhead();
        mydb.adddatabase();
    }

    void start(){

        Log.d("masuk sini lagi","null");

        recyclerView = (RecyclerView) findViewById(R.id.my_item);
        mAdapter = new MyAdapter(itemsArrayList , this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Task.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);


        recyclerView2 = (RecyclerView) findViewById(R.id.my_item2);
        mAdapter2 = new MyAdapter(itemsArrayList2 , this);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(Task.this);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(mAdapter2);

    }

    void showtask() {

        itemsArrayList = new ArrayList<>();
        itemsArrayList2 = new ArrayList<>();
        Cursor res = mydb.getAllData();
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("2" + res.getString(0) + "\n");
            buffer.append("3" + res.getString(1) + "\n");
            buffer.append("4" + res.getString(2) + "\n");
            buffer.append("5" + res.getInt(4) + "\n");
            buffer.append("6" + res.getInt(5) + "\n");
          //  itemsArrayList.add(new items(res.getString(0), res.getString(1), "Tap if to get points"));
            if(res.getInt(4)==0){
            itemsArrayList.add(new items(res.getString(1), res.getInt(2), "Click to Complete", res.getInt(4)));
            }
            if(res.getInt(4)==1){
            itemsArrayList2.add(new items(res.getString(1), res.getInt(2), "Click to Complete", res.getInt(4)));
            }

            Log.d("masuk","showtask");
        }

        // Show

    }

//    void resetscore(View view){
//        Cursor res = mydb.reset();
//
//    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        start();
//    }
    int x = 0;
    public void onItemClick(String namez, int whose, int position) {
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String name = "hi";
        String name2 = "heo";
        if(itemsArrayList.size() > 0){
             name = itemsArrayList.get(position).getName();}
        if(itemsArrayList2.size() > 0){
            name2 = itemsArrayList.get(position).getName();}

        int who = itemsArrayList.get(position).getWhose();

        x++;

        Log.d("masuk rusak ", x+ "   "+who+"   "+whose );

        if( name.equals(namez) && who == whose){
            score += (itemsArrayList.get(position).getPoint());
//          showMessage(name, "Good work!");
            mydb.done(name,(itemsArrayList.get(position).getPoint()));
            ((TextView)findViewById(R.id.my_number)).setText(String.valueOf(score));
            itemsArrayList.remove(position);
            editor.putInt("your_int_key", score);
            editor.commit();


        }else{
            score2 += (itemsArrayList2.get(position).getPoint());
//           showMessage(name2, "Good work!");
            mydb.done(name2,(itemsArrayList2.get(position).getPoint()));
            ((TextView)findViewById(R.id.my_number2)).setText(String.valueOf(score2));
            itemsArrayList2.remove(position);
            editor.putInt("your_int_key2", score2);
            editor.commit();


        }











        start();
    }


//    public void eraseAll(View view) {
//        mydb.eraserhead();
//    }


//    public void Allmi(View view) {
//         mydb.adddatabase();
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
//        start();
//        showMessage("Data", buffer.toString());
//    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void clicknext(View v){
        Intent intent = new Intent (this, Spendpoints.class);
        intent.putExtra("code",1);
        startActivity(intent);
    }

    public void clicknext2(View v){
        Intent intent = new Intent (this, Spendpoints.class);
        intent.putExtra("code",2);
        startActivity(intent);
    }


}
