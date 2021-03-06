package com.example.androidappesiee;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

/*
Activity qui nous permet d'afficher la liste des tâches enregistrées par l'utilisateur
 */
public class CheckitToday extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private RecyclerView mRecyclerView;
    CustomAdapter mCustomAdapter;

    ImageView iv_emptyData;
    TextView tv_emptyData;

    private Button retour_home;

    DataBaseHelper db;
    ArrayList<String> tache_id, tache_nom, tache_description, tache_type, tache_priorite, tache_isdone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_checkittoday);
        mRecyclerView = findViewById(R.id.recyclerToDoView);
        findViewById(android.R.id.content).invalidate();

        iv_emptyData = findViewById(R.id.iv_emptyDatabase);
        tv_emptyData = findViewById(R.id.tv_emptyDatabase);


        db = new DataBaseHelper(CheckitToday.this);
        tache_id = new ArrayList<>();
        tache_nom = new ArrayList<>();
        tache_description = new ArrayList<>();
        tache_type = new ArrayList<>();
        tache_priorite = new ArrayList<>();
        tache_isdone = new ArrayList<>();

        storeDataInArrays();

        mCustomAdapter = new CustomAdapter(CheckitToday.this,CheckitToday.this,
                tache_id,
                tache_nom,
                tache_description,
                tache_type,
                tache_priorite,
                tache_isdone);

        mRecyclerView.setAdapter(mCustomAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(CheckitToday.this));

        //Appel de l'activity main quand le bouton retour est appuyé
        retour_home = (Button) findViewById(R.id.retour_home);
        retour_home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent_retour = new Intent(CheckitToday.this, MainActivity.class);
                startActivity(intent_retour);
                finish();

            }
        });
    }

    //Récupération des données de la database
    void storeDataInArrays() {
        Cursor cursor = db.readAllData();
        if (cursor.getCount() == 0) {
            //Si il n'y a pas de donée, on affiche une certain logo
            Toast.makeText(CheckitToday.this, "No Data in database", Toast.LENGTH_SHORT).show();
            iv_emptyData.setVisibility(View.VISIBLE);
            tv_emptyData.setVisibility(View.VISIBLE);
        } else {
            //Sinon, on n'affiche pas les logos, et on rempli la liste des taches exixtantes
            iv_emptyData.setVisibility(View.GONE);
            tv_emptyData.setVisibility(View.GONE);
            while (cursor.moveToNext()) {
                tache_id.add(cursor.getString(0));
                tache_nom.add(cursor.getString(1));
                tache_description.add(cursor.getString(2));
                tache_type.add(cursor.getString(3));
                tache_priorite.add(cursor.getString(4));
                tache_isdone.add(cursor.getString(5));
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}