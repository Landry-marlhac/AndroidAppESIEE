package com.example.androidappesiee;

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

import androidx.appcompat.app.AppCompatActivity;

public class NewTask extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    //Objet de notre layout
    EditText mNomTache;
    CheckBox mPasimportant;
    CheckBox mImportant;
    CheckBox mTresimportant;
    Spinner mSpinnerTypeDeTache;
    EditText mDescriptionTache;
    Button mEnregistrer;

    //Variables utiles
    String typeDeTache;
    int importance;

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
                Toast.makeText(NewTask.this, "Tâche enregistrée", Toast.LENGTH_LONG).show();
                Intent intentcheck = new Intent(NewTask.this, CheckitToday.class);
                startActivity(intentcheck);
                break;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        typeDeTache = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}