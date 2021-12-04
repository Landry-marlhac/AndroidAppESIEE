package com.example.androidappesiee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class NewTask extends AppCompatActivity {

    CheckBox mpasimportant;
    CheckBox mimportant;
    CheckBox mtresimportant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        mpasimportant = findViewById(R.id.importance1);
        mimportant = findViewById(R.id.importance2);
        mtresimportant = findViewById(R.id.importance3);

        
    }

}