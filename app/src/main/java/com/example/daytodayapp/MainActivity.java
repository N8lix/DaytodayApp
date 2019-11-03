package com.example.daytodayapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  //  public static final String Extra_message = "comcomcom";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void LoginApp(View view) {
//        EditText user = (EditText)findViewById(R.id.IdEdit);
//        String username = user.getText().toString();
//        EditText pass = findViewById(R.id.PasswordEdit);
//        String password = pass.getText().toString();
//
//        if(username.equals("admin") && password.equals("admin")) {
//            Intent intent = new Intent (this, Task.class);
//       //     intent.putExtra(mydb);
//                startActivity(intent);
//        }else{
//            ((TextView)findViewById(R.id.button1)).setText("wrong id / password");
//            ((TextView)findViewById(R.id.button1)).setTextColor(Color.RED);
//        }
        Intent intent = new Intent (this, Task.class);
        startActivity(intent);

    }



}

