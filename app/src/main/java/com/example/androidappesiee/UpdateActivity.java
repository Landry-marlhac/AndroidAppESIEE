package com.example.androidappesiee;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    //Objet de notre layout
    EditText mNomTacheUpdate;
    CheckBox mPasimportantUpdate;
    CheckBox mImportantUpdate;
    CheckBox mTresimportantUpdate;
    Spinner mSpinnerTypeDeTacheUpdate;
    EditText mDescriptionTacheUpdate;
    Button mEnregistrerUpdate, mDeleteTask;

    //Variables utiles
    String typeDeTacheUpdate;
    int importanceUpdate;

    String id,name,description,type,prioritee,isdone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mNomTacheUpdate = findViewById(R.id.editNomTache_update);
        mPasimportantUpdate = findViewById(R.id.importance1_update);
        mPasimportantUpdate.setOnClickListener(this::onClick);
        mImportantUpdate = findViewById(R.id.importance2_update);
        mImportantUpdate.setOnClickListener(this::onClick);
        mTresimportantUpdate = findViewById(R.id.importance3_update);
        mTresimportantUpdate.setOnClickListener(this::onClick);
        mDescriptionTacheUpdate = findViewById(R.id.editDescription_update);
        mEnregistrerUpdate = findViewById(R.id.btn_update);
        mEnregistrerUpdate.setOnClickListener(this::onClick);
        mDeleteTask = findViewById(R.id.btn_delete);
        mDeleteTask.setOnClickListener(this::onClick);
        mSpinnerTypeDeTacheUpdate = (Spinner) findViewById(R.id.spinnerTypeDeTache_update);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_tache_liste, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpinnerTypeDeTacheUpdate.setAdapter(adapter);
        getAndSetIntentData();
        mSpinnerTypeDeTacheUpdate.setOnItemSelectedListener(this);

        //ActionBar Title
        ActionBar ab = getSupportActionBar();
        if(ab!=null){
            ab.setTitle(name);
        }



    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.importance1_update:
                mImportantUpdate.setChecked(false);
                mTresimportantUpdate.setChecked(false);
                importanceUpdate = 0;
                break;

            case R.id.importance2_update:
                mPasimportantUpdate.setChecked(false);
                mTresimportantUpdate.setChecked(false);
                importanceUpdate = 1;
                break;

            case R.id.importance3_update:
                mImportantUpdate.setChecked(false);
                mPasimportantUpdate.setChecked(false);
                importanceUpdate = 2;
                break;

            case R.id.btn_update:
                DataBaseHelper db = new DataBaseHelper(UpdateActivity.this);
                db.updateData(mNomTacheUpdate.getText().toString().trim(),
                        mDescriptionTacheUpdate.getText().toString().trim(),
                        typeDeTacheUpdate, importanceUpdate,false, id);
                Intent checkitIntent = new Intent(this, CheckitToday.class);
                startActivity(checkitIntent);
                finish();
                break;

            case R.id.btn_delete:

                confirmDialog();

                break;

        }
    }


    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") &&
                getIntent().hasExtra("name") &&
                getIntent().hasExtra("description") &&
                getIntent().hasExtra("type") &&
                getIntent().hasExtra("prioritee") &&
                getIntent().hasExtra("isDone")) {

            //Getting data from intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            description = getIntent().getStringExtra("description");
            type = getIntent().getStringExtra("type");
            prioritee = getIntent().getStringExtra("prioritee");
            isdone = getIntent().getStringExtra("isDone");

            //setting intent data
            mNomTacheUpdate.setText(name);
            mDescriptionTacheUpdate.setText(description);
            if(prioritee.equals("0")){
                mPasimportantUpdate.setChecked(true);
                mImportantUpdate.setChecked(false);
                mTresimportantUpdate.setChecked(false);
            }else if(prioritee.equals("1")){
                mPasimportantUpdate.setChecked(false);
                mImportantUpdate.setChecked(true);
                mTresimportantUpdate.setChecked(false);
            }else if (prioritee.equals("2")){
                mPasimportantUpdate.setChecked(false);
                mImportantUpdate.setChecked(false);
                mTresimportantUpdate.setChecked(true);
            }

            switch (type){
                case "Sport":
                    mSpinnerTypeDeTacheUpdate.setSelection(0);
                    break;

                case "Travail":
                    mSpinnerTypeDeTacheUpdate.setSelection(1);
                    break;

                case "Devoirs":
                    mSpinnerTypeDeTacheUpdate.setSelection(2);
                    break;

                case "Everyday life":
                    mSpinnerTypeDeTacheUpdate.setSelection(3);
                    break;

                case "Courses":
                    mSpinnerTypeDeTacheUpdate.setSelection(4);
                    break;

            }


        } else {
            Toast.makeText(UpdateActivity.this, "No data transmitted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        typeDeTacheUpdate = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+name+" ?");
        builder.setMessage("Are you sure you want to delete "+name+" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataBaseHelper db = new DataBaseHelper(UpdateActivity.this);
                db.deleteOne(id);
                Intent checkitIntent = new Intent(UpdateActivity.this, CheckitToday.class);
                startActivity(checkitIntent);
                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    /*A rajouter dans la partie du programme qui gère le delete all

        void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All tasks ?");
        builder.setMessage("Are you sure you want to delete all tasks ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataBaseHelper db = new DataBaseHelper(UpdateActivity.this);
                db.deleteAllData();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

     */



/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        mNomTache = findViewById(R.id.editNomTache);
        mPasimportant = findViewById(R.id.importance1);
        mPasimportant.setOnClickListener(this::onClick);
        mImportant = findViewById(R.id.importance2);
        mImportant.setOnClickListener(this::onClick);
        mTresimportant = findViewById(R.id.importance3);
        mTresimportant.setOnClickListener(this::onClick);
        mDescriptionTache = findViewById(R.id.editDescription);
        mEnregistrer = findViewById(R.id.btn_enregistrer);
        mEnregistrer.setOnClickListener(this::onClick);
        mSpinnerTypeDeTache = (Spinner) findViewById(R.id.spinnerTypeDeTache);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_tache_liste, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpinnerTypeDeTache.setAdapter(adapter);
        mSpinnerTypeDeTache.setOnItemSelectedListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.importance1:
                mImportant.setChecked(false);
                mTresimportant.setChecked(false);
                importance = 0;
                break;

            case R.id.importance2:
                mPasimportant.setChecked(false);
                mTresimportant.setChecked(false);
                importance = 1;
                break;

            case R.id.importance3:
                mImportant.setChecked(false);
                mPasimportant.setChecked(false);
                importance = 2;
                break;

            case R.id.btn_enregistrer:

                DataBaseHelper db = new DataBaseHelper(NewTask.this);
                db.addTask(mNomTache.getText().toString().trim(),
                        mDescriptionTache.getText().toString().trim(),
                        typeDeTache, importance);
                break;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        typeDeTache = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }*/

}
