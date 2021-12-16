package com.example.androidappesiee;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidappesiee.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class CheckitToday extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private RecyclerView mRecyclerView;
    CustomAdapter mCustomAdapter;

    DataBaseHelper db;
    ArrayList<String> tache_id, tache_nom, tache_description, tache_type, tache_priorite, tache_isdone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_checkittoday);


        //Appel de l'activity NewTask quand bouton appuyé


        mRecyclerView = findViewById(R.id.recyclerToDoView);

        db = new DataBaseHelper(CheckitToday.this);
        tache_id = new ArrayList<>();
        tache_nom = new ArrayList<>();
        tache_description = new ArrayList<>();
        tache_type = new ArrayList<>();
        tache_priorite = new ArrayList<>();
        tache_isdone = new ArrayList<>();

        storeDataInArrays();

        mCustomAdapter = new CustomAdapter(CheckitToday.this,
                tache_id,
                tache_nom,
                tache_description,
                tache_type,
                tache_priorite,
                tache_isdone);

        mRecyclerView.setAdapter(mCustomAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(CheckitToday.this));

    }

    void storeDataInArrays() {
        Cursor cursor = db.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(CheckitToday.this, "No Data in database", Toast.LENGTH_SHORT).show();
        } else {
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

    void ToastMessage(String message) {
        Toast.makeText(CheckitToday.this, "" + message, Toast.LENGTH_SHORT).show();
    }

}