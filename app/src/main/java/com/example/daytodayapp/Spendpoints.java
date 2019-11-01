package com.example.daytodayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Spendpoints extends AppCompatActivity {
    private int score = 0;
    private int sumscore   = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spendpoints);

        SharedPreferences mpref = getSharedPreferences("label",0);
        String mString = mpref.getString("tag","0");

        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        int myIntValue = sp.getInt("your_int_key", -1);
        score = myIntValue;
        ((TextView)findViewById(R.id.points)).setText(String.valueOf(score));
    }

    public void clickPlus(View view){
        sumscore ++;
        rfs();
    }

    public void clickMin(View view){
        if(sumscore > 0){
        sumscore--;
        rfs();}
    }
    public void rfs(){
        ((TextView)findViewById(R.id.sum)).setText(String.valueOf(sumscore));
        ((TextView)findViewById(R.id.points)).setText(String.valueOf(score));
    }
    public void calcRes(View view){
        if (sumscore<score){
        score = score - sumscore;
        rfs();}
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("your_int_key", score);
        editor.commit();
    }


}
