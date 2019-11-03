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
    private int myIntValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int id = getIntent().getIntExtra("code",0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spendpoints);
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);

        if(id == 1){
         myIntValue = sp.getInt("your_int_key", -1);
        } else
        {  myIntValue = sp.getInt("your_int_key2", -1);
        }
        score = myIntValue;
        ((TextView)findViewById(R.id.points)).setText(String.valueOf(score));
    }

    public void clickPlus(View view){
        if(score > sumscore){

            sumscore ++;
        rfs();}
    }

    public void clickMin(View view){
        sumscore--;
        rfs();
    }
    public void rfs(){
        ((TextView)findViewById(R.id.sum)).setText(String.valueOf(sumscore));
        ((TextView)findViewById(R.id.points)).setText(String.valueOf(score));
    }
    public void calcRes(View view){
        int id = getIntent().getIntExtra("code",0);

        if (sumscore<=score){
        score = score - sumscore;
        rfs();}
        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();


        if(id == 1){
            editor.putInt("your_int_key", score);
        } else
        {  editor.putInt("your_int_key2", score);
        }



        editor.commit();
    }


}
